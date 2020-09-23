package com.example.streamappkotlin.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.productlist.di.ProductModule
import com.example.streamappkotlin.utils.AppConstants

class ProductDetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var avatar: ImageView
    private lateinit var productName: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var play: ImageView
    private lateinit var commentButton:Button
    private lateinit var progressBar: View
    private lateinit var productDetailViewModel: ProductDetailViewModel
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var productDetailViewModelFactory =
        ProductModule.provideProductDetailViewModelFactory(apiService)
    private lateinit var fileUri: String
    private lateinit var title:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDetailViewModel = ViewModelProviders.of(this, productDetailViewModelFactory)
            .get(ProductDetailViewModel::class.java)

        val productId: Int = requireArguments().getInt("productId")

        avatar = view.findViewById(R.id.productAvatar)
        productName = view.findViewById(R.id.productName)
        play = view.findViewById(R.id.playIcon)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.commentRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        commentButton=view.findViewById(R.id.commentButton)

        observeDetailViewModel()
        productDetailViewModel.setId(productId)
        productDetailViewModel.getProduct()

        commentButton.setOnClickListener {
            val commentDialogFragment=CommentDialogFragment(productId, title)
            commentDialogFragment.show(parentFragmentManager,"CommentDialogFragment")
        }

    }

    private fun observeDetailViewModel() {
        progressBar.visibility = View.VISIBLE
        productDetailViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                progressBar.visibility = View.VISIBLE

            }
        })

        productDetailViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.GONE
                Toast.makeText(context, "Check Your Connection !", Toast.LENGTH_SHORT).show()
            }
        })

        productDetailViewModel.productDetailLiveData.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            productName.text = it.name
            title=it.name
            Glide.with(requireContext()).load(AppConstants.baseUrl + it.avatar.mdpi).into(avatar)
            fileUri = it.files[0].file
        })

        productDetailViewModel.productCommentLiveData.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            showComment(it)
        })
    }

    private fun showComment(commentList: List<Comment>) {
        val adapter = ProductCommentAdapter(requireContext(), commentList)
        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
    }

}
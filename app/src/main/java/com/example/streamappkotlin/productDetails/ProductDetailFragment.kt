package com.example.streamappkotlin.productDetails

import android.content.Intent
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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ethanhua.skeleton.Skeleton
import com.example.streamappkotlin.PlayerActivity
import com.example.streamappkotlin.R
import com.example.streamappkotlin.login.LoginShareViewModel
import com.example.streamappkotlin.login.LoginStepOneDialogFragment
import com.example.streamappkotlin.login.LoginStepTwoListener
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.utils.AppConstants
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var avatar: ImageView
    private lateinit var productName: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var playIcon: ImageView
    private lateinit var commentButton: Button
    private lateinit var progressBar: View
    private val shareViewModel: LoginShareViewModel by sharedViewModel()
    private  val productDetailViewModel: ProductDetailViewModel by inject()

    private lateinit var fileUri: String
    private lateinit var title: String
    private lateinit var adapter: ProductCommentAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId: Int = requireArguments().getInt("productId")

        avatar = view.findViewById(R.id.productAvatar)
        productName = view.findViewById(R.id.productName)
        playIcon = view.findViewById(R.id.playIcon)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.commentRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        commentButton = view.findViewById(R.id.commentButton)

        observeDetailViewModel()
        productDetailViewModel.setId(productId)
        productDetailViewModel.getProduct()

        playIcon.setOnClickListener {
            val intent = Intent(activity, PlayerActivity::class.java)
            intent.putExtra("fileUri", fileUri)
            intent.putExtra("title",title)
            startActivity(intent)
        }

        shareViewModel.isLogin.observeSingleEvent(viewLifecycleOwner, Observer {
            if (it == true) {
                val commentDialogFragment = CommentDialogFragment(productId, title)
                commentDialogFragment.show(parentFragmentManager, "CommentDialogFragment")
            } else {
                val loginStepOneDialogFragment =
                    LoginStepOneDialogFragment(object : LoginStepTwoListener {
                        override fun userExist(exist: Boolean) {
                            val commentDialogFragment = CommentDialogFragment(productId, title)
                            commentDialogFragment.show(
                                parentFragmentManager,
                                "CommentDialogFragment"
                            )
                        }
                    })
                loginStepOneDialogFragment.show(parentFragmentManager, "LoginStepOneDialogFragment")
            }
        })
        commentButton.setOnClickListener {
            shareViewModel.isLogin()
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
            title = it.name
            Glide.with(requireContext()).load(AppConstants.baseUrl + it.avatar.mdpi).into(avatar)
            fileUri = it.files[0].file
        })

        productDetailViewModel.productCommentLiveData.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            showComment(it)
        })
    }

    private fun showComment(commentList: List<Comment>) {
        adapter = ProductCommentAdapter(requireContext(), commentList)
//        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        skeletonLoading()
    }

    private fun skeletonLoading() {
        val skeletonScreen =
            Skeleton.bind(recyclerView).adapter(adapter).shimmer(true).angle(20).frozen(false)
                .duration(900).count(10).load(R.layout.comment_skeleton).show()
        recyclerView.postDelayed({ skeletonScreen.hide() }, 2000)
    }

}
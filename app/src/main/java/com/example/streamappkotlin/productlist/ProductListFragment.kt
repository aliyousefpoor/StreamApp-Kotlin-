package com.example.streamappkotlin.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.productlist.di.ProductModule
import com.google.android.material.appbar.MaterialToolbar

class ProductListFragment : Fragment() {
    private lateinit var pullDown: TextView
    private lateinit var arrow: ImageView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private lateinit var progressBar: View
    private var productListViewModel: ProductListViewModel? = null
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var productListViewModelFactory =
        ProductModule.provideProductListViewModelFactory(apiService)
    private var adapter: ProductListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productListViewModel = ViewModelProviders.of(this, productListViewModelFactory)
            .get(ProductListViewModel::class.java)

        val productListId: Int = requireArguments().getInt("productListId")
        val productListTitle = requireArguments().getString("productListTitle")
        pullDown = view.findViewById(R.id.refresh)
        arrow = view.findViewById(R.id.productArrow)
        swipeRefreshLayout = view.findViewById(R.id.productRefreshing)
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        navController = Navigation.findNavController(view)
        val toolbar: MaterialToolbar = view.findViewById(R.id.listToolbar)
        toolbar.title = productListTitle

        observeProductListViewModel()

        productListViewModel!!.setId(productListId)
        productListViewModel!!.getFirstData()

        pullDown.setOnClickListener {
            productListViewModel!!.loadData()
        }
        swipeRefreshLayout.setOnRefreshListener {
            productListViewModel!!.loadData()
        }
    }

    private fun observeProductListViewModel() {
        pullDown.visibility = View.GONE
        arrow.visibility = View.GONE
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = true

        productListViewModel!!.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                pullDown.visibility = View.GONE
                arrow.visibility = View.GONE
                recyclerView.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = true
            }
        })

        productListViewModel!!.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                pullDown.visibility = View.VISIBLE
                arrow.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, "Check Your Connection !", Toast.LENGTH_SHORT).show()
            }
        })

        productListViewModel!!.productListLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                progressBar.visibility = View.GONE

            } else {
                progressBar.visibility = View.GONE

            }
        })

    }
}
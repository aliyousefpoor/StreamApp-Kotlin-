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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
    private lateinit var productListViewModel: ProductListViewModel
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var productListViewModelFactory =
        ProductModule.provideProductListViewModelFactory(apiService)
    private lateinit var adapter: ProductListAdapter

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
        showProductAdapter()

        swipeRefreshLayout.setOnRefreshListener {
            productListViewModel.loadData()
        }

        productListViewModel.setId(productListId)
        productListViewModel.getFirstData()
    }

    private fun observeProductListViewModel() {
        pullDown.visibility = View.GONE
        arrow.visibility = View.GONE
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = true

        productListViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                pullDown.visibility = View.GONE
                arrow.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = true
            }
        })

        productListViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                pullDown.visibility = View.VISIBLE
                arrow.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, "Check Your Connection !", Toast.LENGTH_SHORT).show()
            }
        })

        productListViewModel.productListLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                swipeRefreshLayout.isRefreshing = false
                progressBar.visibility = View.GONE
                pullDown.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.addList(it)
                adapter.notifyDataSetChanged()

            } else {
                progressBar.visibility = View.GONE
            }
        })

    }

    private fun showProductAdapter() {
        adapter = ProductListAdapter(requireContext(), object : ProductListener {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt("productId",id)
                navController.navigate(R.id.action_productListFragment_to_productDetailFragment,bundle)
            }

        })
        recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener)
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisibleItemPosition: Int =
                (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            if (lastVisibleItemPosition == recyclerView.adapter!!.itemCount - 1) {
                productListViewModel.loadData()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }

}
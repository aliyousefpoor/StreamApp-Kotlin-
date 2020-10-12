package com.example.streamappkotlin.hometab

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ethanhua.skeleton.Skeleton
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.hometab.adapter.MultipleAdapter
import com.example.streamappkotlin.hometab.di.HomeTabModule
import com.example.streamappkotlin.model.HomeItem
import com.example.streamappkotlin.model.Product
import com.example.streamappkotlin.model.Store

class HomeFragment : Fragment() {
    private lateinit var arrow: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pullDown: TextView
    private lateinit var swipeRefreshing: SwipeRefreshLayout
    private lateinit var navController: NavController
    private lateinit var homeViewModel: HomeViewModel
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var homeViewModelFactory = HomeTabModule.provideHomeViewModelFactory(apiService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel =
            ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel::class.java)

        arrow = view.findViewById(R.id.arrow)
        pullDown = view.findViewById(R.id.pullDown)
        recyclerView = view.findViewById(R.id.rec_view)
        swipeRefreshing = view.findViewById(R.id.homeSwipeRefreshing)
        navController = Navigation.findNavController(view)
        swipeRefreshing.setOnRefreshListener {

            homeViewModel.getStore()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        pullDown.visibility = View.GONE
        arrow.visibility = View.GONE
        swipeRefreshing.isRefreshing = true

        homeViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                pullDown.visibility = View.GONE
                arrow.visibility = View.GONE
                recyclerView.visibility = View.GONE
                swipeRefreshing.isRefreshing = true
            }
        })
        homeViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                pullDown.visibility = View.VISIBLE
                arrow.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                swipeRefreshing.isRefreshing = false
                Toast.makeText(context, "Check Your Connection !", Toast.LENGTH_SHORT).show()
            }
        })
        homeViewModel.storeListLiveData.observe(viewLifecycleOwner, Observer {
            swipeRefreshing.isRefreshing = false
            recyclerView.visibility = View.VISIBLE
            arrow.visibility = View.GONE
            showStoreData(it)
        })
    }

    private fun showStoreData(response: Store) {
        val homeList: List<HomeItem> = response.homeitem
        val headerList: List<Product> = response.headeritem

        val adapter =
            MultipleAdapter(
                requireContext(),
                homeList,
                headerList, object : ProductListener {
                    override fun onClick(id: Int) {
                        val bundle = Bundle()
                        bundle.putInt("productId", id)
                        navController.navigate(
                            R.id.action_homeFragment_to_productDetailFragment,
                            bundle
                        )
                    }
                })
        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
    }
}
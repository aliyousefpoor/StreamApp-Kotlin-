package com.example.streamappkotlin.hometab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.streamappkotlin.R
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.di.AppModule
import com.example.streamappkotlin.hometab.di.HomeTabModule
import com.example.streamappkotlin.model.Store

class HomeFragment : Fragment() {
    private lateinit var arrow: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pullDown: TextView
    private lateinit var swipeRefreshing: SwipeRefreshLayout
    private var homeViewModel: HomeViewModel? = null
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var homeViewModelFactory =HomeTabModule.provideHomeViewModelFactory(apiService)

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

        swipeRefreshing.setOnRefreshListener {
            homeViewModel!!.getStore()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel?.storeListLiveData?.observe(viewLifecycleOwner, Observer {
            showStoreData(it)
        })
    }

    private fun showStoreData(response: Store) {

    }
}
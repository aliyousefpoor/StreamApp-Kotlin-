package com.example.streamappkotlin.categorytab

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
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.CategoryListener
import com.example.streamappkotlin.R
import com.example.streamappkotlin.categorytab.di.CategoryTabModule
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.model.Category

class CategoryFragment : Fragment() {
    private lateinit var arrow: ImageView
    private lateinit var pullDown: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    private var categoryViewModel: CategoryViewModel? = null
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var categoryViewModelFactory =
        CategoryTabModule.provideCategoryViewModelFactory(apiService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel =
            ViewModelProviders.of(this, categoryViewModelFactory).get(CategoryViewModel::class.java)
        arrow = view.findViewById(R.id.cat_arrow)
        pullDown = view.findViewById(R.id.pullDown)
        swipeRefreshLayout = view.findViewById(R.id.refreshing)
        recyclerView = view.findViewById(R.id.recycler_view)
        navController = Navigation.findNavController(view)

        swipeRefreshLayout.setOnRefreshListener {
            categoryViewModel!!.getCategory()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        pullDown.visibility = View.GONE
        arrow.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = true

        categoryViewModel?.loadingLiveData?.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                pullDown.visibility = View.GONE
                arrow.visibility = View.GONE
                recyclerView.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = true
            }
        })
        categoryViewModel?.errorLiveData?.observe(viewLifecycleOwner, Observer {
            if (it) {
                pullDown.visibility = View.VISIBLE
                arrow.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, "Check Your Connection !", Toast.LENGTH_SHORT).show()
            }
        })
        categoryViewModel?.categoryList?.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            showCategoryList(it)
        })
    }

    private fun showCategoryList(response: List<Category>) {
        val categoryList: List<Category> = response
        val adapter = CategoryAdapter(categoryList, requireContext(), object : CategoryListener {
            override fun onClick(id: Int?, title: String?) {
                val bundle = Bundle()
                bundle.putInt("productListId", id!!)
                bundle.putString("productListTitle", title)
                navController.navigate(R.id.action_categoryFragment_to_productListFragment, bundle)
            }

        })
        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
    }
}
package com.example.streamappkotlin.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.productlist.di.ProductModule
import com.google.android.material.appbar.MaterialToolbar

class ProductListFragment : Fragment() {

    private var productListViewModel: ProductListViewModel? = null
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var productListViewModelFactory =
        ProductModule.provideProductListViewModelFactory(apiService)

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
        val toolbar: MaterialToolbar = view.findViewById(R.id.listToolbar)
        toolbar.title = productListTitle


    }
}
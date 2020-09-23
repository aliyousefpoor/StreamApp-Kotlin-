package com.example.streamappkotlin.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.ProductListRemoteDataSource
import java.lang.IllegalArgumentException

class ProductListViewModelFactory(private var productListRemoteDataSource: ProductListRemoteDataSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(productListRemoteDataSource) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}
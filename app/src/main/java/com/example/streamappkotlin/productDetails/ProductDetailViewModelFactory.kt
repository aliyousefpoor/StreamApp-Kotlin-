package com.example.streamappkotlin.productDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource
import java.lang.IllegalArgumentException

class ProductDetailViewModelFactory(private var productDetailRemoteDataSource: ProductDetailRemoteDataSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            return ProductDetailViewModel(productDetailRemoteDataSource) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}
package com.example.streamappkotlin.productDetails

import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource

class ProductDetailViewModel(private var productDetailRemoteDataSource: ProductDetailRemoteDataSource) :
    ViewModel() {

}
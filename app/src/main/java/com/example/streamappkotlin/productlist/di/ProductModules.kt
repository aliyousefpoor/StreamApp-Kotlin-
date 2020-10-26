package com.example.streamappkotlin.productlist.di

import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource
import com.example.streamappkotlin.datasource.remote.ProductListRemoteDataSource
import com.example.streamappkotlin.datasource.remote.SendCommentRemoteDataSource
import com.example.streamappkotlin.productDetails.ProductDetailViewModel
import com.example.streamappkotlin.productDetails.SendCommentViewModel
import com.example.streamappkotlin.productlist.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModules = module {

    factory {
        ProductListRemoteDataSource(get())
    }
    viewModel {
        ProductListViewModel(get()) }

    factory {
        ProductDetailRemoteDataSource(get())
    }
    viewModel {
        ProductDetailViewModel(get())
    }

    factory {
        SendCommentRemoteDataSource(get())
    }
    viewModel {
        SendCommentViewModel(get())
    }
}
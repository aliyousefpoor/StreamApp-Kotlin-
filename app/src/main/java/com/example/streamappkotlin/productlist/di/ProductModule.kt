package com.example.streamappkotlin.productlist.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource
import com.example.streamappkotlin.datasource.remote.ProductListRemoteDataSource
import com.example.streamappkotlin.productDetails.ProductDetailViewModelFactory
import com.example.streamappkotlin.productlist.ProductListViewModelFactory

class ProductModule {
    companion object {
        private fun provideProductListRemoteDataSource(apiService: ApiService): ProductListRemoteDataSource {
            return ProductListRemoteDataSource(apiService)
        }

        fun provideProductListViewModelFactory(apiService: ApiService): ProductListViewModelFactory {
            return ProductListViewModelFactory(provideProductListRemoteDataSource(apiService))
        }

        private fun provideProductDetailRemoteDataSource(apiService: ApiService): ProductDetailRemoteDataSource {
            return ProductDetailRemoteDataSource(apiService)
        }

        fun provideProductDetailViewModelFactory(apiService: ApiService): ProductDetailViewModelFactory {
            return ProductDetailViewModelFactory(provideProductDetailRemoteDataSource(apiService))
        }
    }
}
package com.example.streamappkotlin.categorytab.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.categorytab.CategoryViewModelFactory
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource

class CategoryTabModule {
    companion object {
        private fun provideCategoryRemoteDataSource(apiService: ApiService): CategoryRemoteDataSource {
            return CategoryRemoteDataSource(apiService)
        }

        fun provideCategoryViewModelFactory(apiService: ApiService): CategoryViewModelFactory {
            return CategoryViewModelFactory(
                provideCategoryRemoteDataSource(apiService)
            )
        }
    }
}
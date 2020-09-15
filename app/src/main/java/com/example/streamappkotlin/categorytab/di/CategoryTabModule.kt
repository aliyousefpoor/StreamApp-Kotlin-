package com.example.streamappkotlin.categorytab.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource

class CategoryTabModule {
    companion object {
        fun provideCategoryRemoteDataSource(apiService: ApiService): CategoryRemoteDataSource {
            return CategoryRemoteDataSource(apiService)
        }

    }
}
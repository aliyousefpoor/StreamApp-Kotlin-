package com.example.streamappkotlin.categorytab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource
import java.lang.IllegalArgumentException

class CategoryViewModelFactory : ViewModelProvider.Factory {
    private var categoryRemoteDataSource: CategoryRemoteDataSource

    constructor(categoryRemoteDataSource: CategoryRemoteDataSource) {
        this.categoryRemoteDataSource = categoryRemoteDataSource
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(categoryRemoteDataSource) as T
        }
        throw IllegalArgumentException("UnKnown Class")
    }
}
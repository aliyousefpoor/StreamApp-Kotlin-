package com.example.streamappkotlin.categorytab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource
import java.lang.IllegalArgumentException

class CategoryViewModelFactory(private var categoryRemoteDataSource: CategoryRemoteDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(categoryRemoteDataSource) as T
        }
        throw IllegalArgumentException("UnKnown Class")
    }
}
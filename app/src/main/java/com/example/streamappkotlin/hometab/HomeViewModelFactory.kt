package com.example.streamappkotlin.hometab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private var homeRemoteDataSource: HomeRemoteDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRemoteDataSource) as T
        }
        throw IllegalArgumentException("UnKnown Class")
    }
}
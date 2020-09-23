package com.example.streamappkotlin.hometab.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource
import com.example.streamappkotlin.hometab.HomeViewModelFactory

class HomeTabModule {
    companion object {
        private fun provideHomeRemoteDataSource(apiService: ApiService): HomeRemoteDataSource {
            return HomeRemoteDataSource(apiService)
        }

        fun provideHomeViewModelFactory(apiService: ApiService): HomeViewModelFactory {
            return HomeViewModelFactory(provideHomeRemoteDataSource(apiService))
        }
    }
}
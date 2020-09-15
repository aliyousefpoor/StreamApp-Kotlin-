package com.example.streamappkotlin.hometab.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource

class HomeTabModule {
    fun provideHomeRemoteDataSource(apiService: ApiService):HomeRemoteDataSource {
        return HomeRemoteDataSource(apiService)
    }
}
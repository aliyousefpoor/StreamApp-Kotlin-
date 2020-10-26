package com.example.streamappkotlin.hometab.di

import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource
import com.example.streamappkotlin.hometab.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModules = module {

    factory { HomeRemoteDataSource(get()) }
    viewModel { HomeViewModel(get()) }
}
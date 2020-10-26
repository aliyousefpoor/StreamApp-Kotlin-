package com.example.streamappkotlin.categorytab.di

import com.example.streamappkotlin.categorytab.CategoryViewModel
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoryModules = module {

    factory {
        CategoryRemoteDataSource(get())
    }
    viewModel {
        CategoryViewModel(get())
    }
}
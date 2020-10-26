package com.example.streamappkotlin.profile

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModules = module {

    viewModel {
        ProfileViewModel(get())
    }
}
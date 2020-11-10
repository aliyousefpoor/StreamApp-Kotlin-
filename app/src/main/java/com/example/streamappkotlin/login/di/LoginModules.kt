package com.example.streamappkotlin.login.di

import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.locale.database.UserDao
import com.example.streamappkotlin.datasource.locale.database.UserDatabase
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.datasource.remote.LoginStepTwoRemoteDataSource
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl
import com.example.streamappkotlin.login.LoginShareViewModel
import com.example.streamappkotlin.repository.LoginRepository
import com.example.streamappkotlin.repository.ProfileRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    factory {
        LoginStepOneRemoteDataSource(get())
    }
    factory {
        LoginStepTwoRemoteDataSource(get())
    }
    factory {
        provideDataBase().userDao()
    }
    factory {
        UserLocaleDataSourceImp(get())
    }
    factory {
        LoginRepository(get(), get(), get())
    }
    factory {
        UserRemoteDataSourceImpl(get())
    }
    factory {
        ProfileRepository(get(), get())
    }
    viewModel {
        LoginShareViewModel(get())
    }
}

fun provideDataBase(): UserDatabase {
    val context = CustomApp.getContext()
    return UserDatabase.getInstance(context)
}

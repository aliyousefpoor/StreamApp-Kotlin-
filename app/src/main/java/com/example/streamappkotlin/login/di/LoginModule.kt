package com.example.streamappkotlin.login.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource

class LoginModule {
    companion object{
        fun provideLoginStepOneRemoteDataSource(apiService: ApiService):LoginStepOneRemoteDataSource{
            return LoginStepOneRemoteDataSource(apiService)
        }

    }
}
package com.example.streamappkotlin.login.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.datasource.remote.LoginStepTwoRemoteDataSource
import com.example.streamappkotlin.login.LoginShareViewModelFactory
import com.example.streamappkotlin.repository.LoginRepository

class LoginModule {
    companion object {
        private fun provideLoginStepOneRemoteDataSource(apiService: ApiService): LoginStepOneRemoteDataSource {
            return LoginStepOneRemoteDataSource(apiService)
        }
        private fun provideLoginStepTwoRemoteDataSource(apiService: ApiService):LoginStepTwoRemoteDataSource{
            return LoginStepTwoRemoteDataSource(apiService)
        }

        fun provideLoginRepository(apiService: ApiService): LoginRepository {
            return LoginRepository(provideLoginStepOneRemoteDataSource(apiService),
                provideLoginStepTwoRemoteDataSource(apiService))
        }
        fun provideLoginShareViewModelFactory(loginRepository: LoginRepository):LoginShareViewModelFactory{
            return LoginShareViewModelFactory(loginRepository)
        }
    }
}
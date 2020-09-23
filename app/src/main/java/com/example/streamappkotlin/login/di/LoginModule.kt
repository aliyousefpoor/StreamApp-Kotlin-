package com.example.streamappkotlin.login.di

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.locale.database.UserDao
import com.example.streamappkotlin.datasource.locale.database.UserDatabase
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.datasource.remote.LoginStepTwoRemoteDataSource
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl
import com.example.streamappkotlin.login.LoginShareViewModelFactory
import com.example.streamappkotlin.profile.ProfileViewModelFactory
import com.example.streamappkotlin.repository.LoginRepository
import com.example.streamappkotlin.repository.ProfileRepository

class LoginModule {
    companion object {
        private fun provideLoginStepOneRemoteDataSource(apiService: ApiService): LoginStepOneRemoteDataSource {
            return LoginStepOneRemoteDataSource(apiService)
        }

        private fun provideLoginStepTwoRemoteDataSource(apiService: ApiService): LoginStepTwoRemoteDataSource {
            return LoginStepTwoRemoteDataSource(apiService)
        }

        fun provideLoginRepository(apiService: ApiService, userDao: UserDao): LoginRepository {
            return LoginRepository(
                provideLoginStepOneRemoteDataSource(apiService),
                provideLoginStepTwoRemoteDataSource(apiService),
                provideUserLocaleDataSourceImp(userDao)
            )
        }

        fun provideLoginShareViewModelFactory(loginRepository: LoginRepository): LoginShareViewModelFactory {
            return LoginShareViewModelFactory(loginRepository)
        }

         fun provideUserLocaleDataSourceImp(userDao: UserDao): UserLocaleDataSourceImp {
            return UserLocaleDataSourceImp(userDao)
        }

        private fun provideUserRemoteDataSource(apiService: ApiService): UserRemoteDataSourceImpl {
            return UserRemoteDataSourceImpl(apiService)
        }

        private fun provideProfileRepository(
            userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
            userLocaleDataSourceImp: UserLocaleDataSourceImp
        ): ProfileRepository {
            return ProfileRepository(userRemoteDataSourceImpl, userLocaleDataSourceImp)
        }

         fun provideProfileViewModelFactory(apiService: ApiService,userDao: UserDao): ProfileViewModelFactory {
            return ProfileViewModelFactory(
                provideProfileRepository(
                    provideUserRemoteDataSource(apiService),
                    provideUserLocaleDataSourceImp(userDao)
                )
            )
        }

        fun provideUserDatabase(): UserDatabase {
            val context = CustomApp.getContext()
            return UserDatabase.getInstance(context)
        }
    }
}
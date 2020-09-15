package com.example.streamappkotlin.di

import com.example.streamappkotlin.ApiBuilder
import com.example.streamappkotlin.ApiService
import retrofit2.Retrofit

class ApiBuilderModule {
    fun provideApiService(builder: ApiBuilder):ApiService {
        return builder.create(ApiService::class.java)
    }
    fun provideApiBuilder(retrofit: Retrofit) : ApiBuilder{
        return ApiBuilder(retrofit)
    }
}
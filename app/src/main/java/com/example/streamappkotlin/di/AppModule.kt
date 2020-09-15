package com.example.streamappkotlin.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {
    var retrofit: Retrofit? = null

    fun provideRetrofit(): Retrofit? {
        var loggingInterceptor: HttpLoggingInterceptor? =null
        loggingInterceptor?.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient : OkHttpClient.Builder? =null
        httpClient?.addInterceptor(loggingInterceptor)

        if (retrofit == null) {
            if (httpClient != null) {
                retrofit = Retrofit.Builder().baseUrl("https://api.vasapi.click/")
                    .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
            }
        }
        return retrofit
    }
}
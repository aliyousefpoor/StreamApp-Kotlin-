package com.example.streamappkotlin.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {
    private var retrofit: Retrofit? = null

    fun provideRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)

        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("https://api.vasapi.click/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()).build()
        }
        return retrofit!!
    }
}
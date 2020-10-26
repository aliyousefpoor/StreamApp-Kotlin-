package com.example.streamappkotlin

import com.example.streamappkotlin.di.TokenInterceptor
import com.example.streamappkotlin.utils.ApiBuilder
import com.example.streamappkotlin.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        provideRetrofit()
    }
    factory {
        apiBuilder(get())
    }

    factory {
        apiService(get())
    }
}
private var retrofit: Retrofit? = null
fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    httpClient.addInterceptor(loggingInterceptor)
    httpClient.addInterceptor(TokenInterceptor())

    if (retrofit == null) {
        retrofit = Retrofit.Builder().baseUrl(AppConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }
    return retrofit!!
}

fun apiBuilder(retrofit: Retrofit): ApiBuilder {
    return ApiBuilder(retrofit)
}

fun apiService(builder: ApiBuilder): ApiService {
    return builder.create(ApiService::class.java)
}

package com.example.streamappkotlin.utils

import retrofit2.Retrofit

class ApiBuilder(var retrofit: Retrofit) {

    fun <T> create(serviceInterface: Class<T>): T {
        return retrofit.create(serviceInterface)
    }
}
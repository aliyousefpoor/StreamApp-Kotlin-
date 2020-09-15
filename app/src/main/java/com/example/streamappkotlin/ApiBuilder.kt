package com.example.streamappkotlin

import retrofit2.Retrofit

class ApiBuilder {
    lateinit var retrofit: Retrofit

    constructor(retrofit: Retrofit) {
        this.retrofit = retrofit
    }

    fun <T> create(serviceInterface: Class<T>): T {
        return retrofit.create(serviceInterface)
    }
}
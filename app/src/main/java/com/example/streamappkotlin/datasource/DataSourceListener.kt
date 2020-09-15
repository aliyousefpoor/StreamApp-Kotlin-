package com.example.streamappkotlin.datasource

interface DataSourceListener<T> {
    fun onResponse(response:T)
    fun onFailure(throwable: Throwable)
}
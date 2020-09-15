package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRemoteDataSource {
    private var apiService: ApiService

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }

    fun getStore(dataSourceListener: DataSourceListener<Store>) {
        apiService.getStore().enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }


        })
    }
}
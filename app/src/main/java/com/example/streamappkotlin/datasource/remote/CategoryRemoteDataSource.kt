package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {
    private var apiService: ApiService

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }

    fun getCategory(dataSourceListener: DataSourceListener<List<Category>>) {
        apiService.getCategory().enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>,response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }


        })


    }
}
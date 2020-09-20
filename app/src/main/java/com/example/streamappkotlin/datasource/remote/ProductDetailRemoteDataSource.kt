package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailRemoteDataSource(private var apiService: ApiService) {

    fun getProductDetails(id: Int, dataSourceListener: DataSourceListener<Product>) {
        apiService.getProductDetail(id).enqueue(object : Callback<Product> {

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                dataSourceListener.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }

        })
    }

    fun getComment(id: Int, dataSourceListener: DataSourceListener<List<Comment>>) {

        apiService.getComment(id).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                dataSourceListener.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }

        })
    }
}
package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListRemoteDataSource(private var apiService: ApiService) {

    fun getProductList(
        id: Int,
        offset: Int,
        dataSourceListener: DataSourceListener<List<Product>>) {
        apiService.getProductList(id, 20, offset)
            .enqueue(object : Callback<List<Product>> {

                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    dataSourceListener.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    dataSourceListener.onFailure(t)
                }
            })
    }
}
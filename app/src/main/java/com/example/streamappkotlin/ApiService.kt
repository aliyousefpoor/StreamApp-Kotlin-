package com.example.streamappkotlin

import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("category/16/463")
    fun getCategory(): Call<List<Category>>

    @GET("listproducts/{categoryId}")
    fun getProductList(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<List<Product>>

    @GET("product/{productId}?device_os=ios")
    fun getProductDetail(@Path("productId") productId: Int): Call<Product>
}
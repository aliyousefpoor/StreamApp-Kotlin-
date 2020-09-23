package com.example.streamappkotlin

import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Store
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("store/16") fun getStore():Call<Store>
    @GET("category/16/463") fun getCategory(): Call<List<Category>>
}
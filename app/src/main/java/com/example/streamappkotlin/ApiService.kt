package com.example.streamappkotlin

import com.example.streamappkotlin.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("category/16/463") fun getCategory(): Call<List<Category>>
}
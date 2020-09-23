package com.example.streamappkotlin

import com.example.streamappkotlin.model.*
import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Store
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("store/16") fun getStore():Call<Store>
    @GET("category/16/463") fun getCategory(): Call<List<Category>>
    @POST("mobile_login_step1/16")
    fun loginStepOne(@Body loginStepOneRequest: LoginStepOneRequest): Call<LoginStepOneResponse>

    @POST("mobile_login_step2/16")
    fun loginStepTwo(@Body loginStepTwoRequest: LoginStepTwoRequest): Call<LoginStepTwoResponse>
}
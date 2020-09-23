package com.example.streamappkotlin

import com.example.streamappkotlin.model.*
import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Store
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("store/16")
    fun getStore(): Call<Store>
    @GET("category/16/463")
    fun getCategory(): Call<List<Category>>

    @POST("mobile_login_step1/16")
    fun loginStepOne(@Body loginStepOneRequest: LoginStepOneRequest): Call<LoginStepOneResponse>

    @POST("mobile_login_step2/16")
    fun loginStepTwo(@Body loginStepTwoRequest: LoginStepTwoRequest): Call<LoginStepTwoResponse>

    @POST("profile")
    fun update(
        @Header("Authorization") token: String,
        @Body updateProfile: UpdateProfile
    ): Call<UpdateResponse>

    @GET("profile")
    fun getUser(@Header("Authorization") token: String): Call<ProfileResponse>

    @Multipart
    @POST("profile")
    fun updateImage(
        @Header("Authorization") token: String,
        @Part avatar: MultipartBody.Part
    ): Call<UpdateResponse>

      @GET("listproducts/{categoryId}")
    fun getProductList(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<List<Product>>
}
package com.example.streamappkotlin

import com.example.streamappkotlin.model.*
import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.Product
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("store/16")
    fun getStore(): Call<Store>
    @GET("store/16")
    fun rxGetStore():Observable<Store>

    @GET("category/16/463")
    fun getCategory(): Call<List<Category>>
    @GET("category/16/463")
    fun rxGetCategory():Observable<List<Category>>

    @POST("mobile_login_step1/16")
    fun loginStepOne(@Body loginStepOneRequest: LoginStepOneRequest): Call<LoginStepOneResponse>
    @POST("mobile_login_step1/16")
    fun rxLoginStepOne(@Body loginStepOneRequest: LoginStepOneRequest):Observable<LoginStepOneResponse>

    @POST("mobile_login_step2/16")
    fun loginStepTwo(@Body loginStepTwoRequest: LoginStepTwoRequest): Call<LoginStepTwoResponse>
    @POST("mobile_login_step2/16")
    fun rxLoginStepTwo(@Body loginStepTwoRequest: LoginStepTwoRequest):Observable<LoginStepTwoResponse>


    @POST("profile")
    fun update(
        @Body updateProfile: UpdateProfile
    ): Call<UpdateResponse>

    @GET("profile")
    fun getUser(): Call<ProfileResponse>

    @Multipart
    @POST("profile")
    fun updateImage(
        @Part avatar: MultipartBody.Part
    ): Call<UpdateResponse>

      @GET("listproducts/{categoryId}")
    fun getProductList(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<List<Product>>

    @GET("product/{productId}?device_os=ios")
    fun getProductDetail(@Path("productId") productId: Int): Call<Product>

    @GET("comment/{productId}")
    fun getComment(@Path("productId") productId: Int): Call<List<Comment>>

    @POST("comment/{productId}")
    @FormUrlEncoded
    fun sendComment(
        @Field("title") title: String,
        @Field("score") score: Int,
        @Field("comment_text") commentText: String,
        @Path("productId") productId: Int
    ): Call<CommentPostResponse>
}
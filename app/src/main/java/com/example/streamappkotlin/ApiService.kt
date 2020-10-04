package com.example.streamappkotlin

import com.example.streamappkotlin.model.*
import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.Product
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("store/16")
    fun getStore(): Observable<Store>

    @GET("category/16/463")
    fun getCategory(): Observable<List<Category>>

    @POST("mobile_login_step1/16")
    fun loginStepOne(@Body loginStepOneRequest: LoginStepOneRequest): Observable<LoginStepOneResponse>

    @POST("mobile_login_step2/16")
    fun loginStepTwo(@Body loginStepTwoRequest: LoginStepTwoRequest): Observable<LoginStepTwoResponse>

    @POST("profile")
    fun update(@Body updateProfile: UpdateProfile): Observable<UpdateResponse>

    @GET("profile")
    fun getUser(): Observable<ProfileResponse>

    @Multipart
    @POST("profile")
    fun updateImage(@Part avatar: MultipartBody.Part):Observable<UpdateResponse>

    @GET("listproducts/{categoryId}")
    fun getProductList(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int):Observable<List<Product>>

    @GET("product/{productId}?device_os=ios")
fun getProductDetail(@Path("productId") productId:Int):Observable<Product>

    @GET("comment/{productId}")
    fun getComment(@Path("productId")productId: Int):Observable<List<Comment>>

    @POST("comment/{productId}")
    @FormUrlEncoded
    fun sendComment(
        @Field("title") title: String,
        @Field("score") score: Int,
        @Field("comment_text") commentText: String,
        @Path("productId") productId: Int
    ): Observable<CommentPostResponse>

}
package com.example.streamappkotlin

import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.Product
import retrofit2.Call
import retrofit2.http.*

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

    @GET("comment/{productId}")
    fun getComment(@Path("productId") productId: Int): Call<List<Comment>>

    @POST("comment/{productId}")
    @FormUrlEncoded
    fun sendComment(
        @Field("title") title: String,
        @Field("score") score: Int,
        @Field("comment_text") commentText: String,
        @Path("productId") productId: Int,
        @Header("Authorization") token: String
    ): Call<CommentPostResponse>
}
package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.SendComment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendCommentRemoteDataSource(private var apiService: ApiService) {


    fun sendComment(sendComment: SendComment, dataSourceListener: DataSourceListener<CommentPostResponse>) {
        apiService.sendComment(sendComment.title,
            sendComment.score,
            sendComment.commentText,
            sendComment.productId).enqueue(object : Callback<CommentPostResponse> {
            override fun onResponse(
                call: Call<CommentPostResponse>,
                response: Response<CommentPostResponse>
            ) {
                dataSourceListener.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<CommentPostResponse>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }

        })
    }
}
package com.example.streamappkotlin.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.SendCommentRemoteDataSource
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.SendComment

class SendCommentViewModel(private var sendCommentRemoteDataSource: SendCommentRemoteDataSource) :
    ViewModel() {

    private var _commentResponse: MutableLiveData<CommentPostResponse> = MutableLiveData()
    var commentResponse: LiveData<CommentPostResponse> = _commentResponse


    fun sendComment(sendComment: SendComment) {
        sendCommentRemoteDataSource.sendComment(sendComment,
            object : DataSourceListener<CommentPostResponse> {
                override fun onResponse(response: CommentPostResponse) {
                    _commentResponse.value = response
                }

                override fun onFailure(throwable: Throwable) {
                    _commentResponse.value = null
                }

            })
    }
}
package com.example.streamappkotlin.productDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.SendCommentRemoteDataSource
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.SendComment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class SendCommentViewModel(private var sendCommentRemoteDataSource: SendCommentRemoteDataSource) :
    ViewModel() {
    private val TAG = "SendCommentViewModel"
    private var _commentResponse: MutableLiveData<CommentPostResponse> = MutableLiveData()
    var commentResponse: LiveData<CommentPostResponse> = _commentResponse


    fun sendComment(sendComment: SendComment) {
        sendCommentRemoteDataSource.sendComment(
            sendComment,
            object : Observer<CommentPostResponse> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: ")
                }

                override fun onNext(t: CommentPostResponse) {
                    _commentResponse.value = t
                }

                override fun onError(e: Throwable) {
                    _commentResponse.value = null
                }

            })
    }
}
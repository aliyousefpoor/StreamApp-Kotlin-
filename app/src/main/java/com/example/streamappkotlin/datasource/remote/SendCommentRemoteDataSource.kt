package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.SendComment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendCommentRemoteDataSource(private var apiService: ApiService) {


    fun sendComment(sendComment: SendComment, observer: Observer<CommentPostResponse>) {
        val sendCommentObservable = apiService.rxSendComment(
            sendComment.title,
            sendComment.score,
            sendComment.commentText,
            sendComment.productId
        )
        sendCommentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
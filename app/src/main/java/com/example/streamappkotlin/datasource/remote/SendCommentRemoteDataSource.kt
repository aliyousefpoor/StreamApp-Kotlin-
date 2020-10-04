package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.CommentPostResponse
import com.example.streamappkotlin.model.SendComment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SendCommentRemoteDataSource(private var apiService: ApiService) {


    fun sendComment(sendComment: SendComment, observer: Observer<CommentPostResponse>) {
        val sendCommentObservable = apiService.sendComment(
            sendComment.title,
            sendComment.score,
            sendComment.commentText,
            sendComment.productId
        )
        sendCommentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
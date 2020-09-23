package com.example.streamappkotlin.productDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamappkotlin.datasource.remote.SendCommentRemoteDataSource
import java.lang.IllegalArgumentException

class SendCommentViewModelFactory(private var sendCommentRemoteDataSource: SendCommentRemoteDataSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendCommentViewModel::class.java)) {
            return SendCommentViewModel(sendCommentRemoteDataSource) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}
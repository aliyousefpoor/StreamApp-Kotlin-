package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.Product
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductDetailRemoteDataSource(private var apiService: ApiService) {

    fun getProductDetails(id: Int, observer: Observer<Product>) {
        val productDetailsObservable = apiService.getProductDetail(id)
        productDetailsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    fun getComment(
        id: Int,
        observer: Observer<List<Comment>>
    ) {
        val getCommentObservable = apiService.getComment(id)
        getCommentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.Product
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailRemoteDataSource(private var apiService: ApiService) {

    fun getProductDetails(id: Int, observer: Observer<Product>) {
        val productDetailsObservable = apiService.rxGetProductDetail(id)
        productDetailsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    fun getComment(
        id: Int,
        observer: Observer<List<Comment>>
    ) {
        val getCommentObservable = apiService.rxGetComment(id)
        getCommentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
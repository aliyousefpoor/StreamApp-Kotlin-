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

//        apiService.getProductDetail(id).enqueue(object : Callback<Product> {
//
//            override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                dataSourceListener.onResponse(response.body()!!)
//            }
//
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                dataSourceListener.onFailure(t)
//            }
//
//        })
    }

    fun getComment(id: Int, observer: Observer<List<Comment>>) {
        val getCommentObservable = apiService.rxGetComment(id)
        getCommentObservable.observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)

//        apiService.getComment(id).enqueue(object : Callback<List<Comment>> {
//            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
//                dataSourceListener.onResponse(response.body()!!)
//            }
//
//            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
//                dataSourceListener.onFailure(t)
//            }
//
//        })
    }
}
package com.example.streamappkotlin.datasource.remote

import android.annotation.SuppressLint
import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.Store
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeRemoteDataSource(private var apiService: ApiService) {
//    fun getStore(dataSourceListener: DataSourceListener<Store>) {
//        apiService.getStore().enqueue(object : Callback<Store> {
//            override fun onResponse(call: Call<Store>, response: Response<Store>) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        dataSourceListener.onResponse(response.body()!!)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Store>, t: Throwable) {
//                dataSourceListener.onFailure(t)
//            }
//
//
//        })
//    }

    fun getStoreRx(observer: Observer<Store>) {
        val getStoreObservable :Observable<Store> = apiService.rxGetStore()

        getStoreObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
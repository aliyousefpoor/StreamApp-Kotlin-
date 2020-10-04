package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.Category
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryRemoteDataSource(private var apiService: ApiService) {

    fun getCategory(observer: Observer<List<Category>>) {
        val categoryObservable = apiService.getCategory()
        categoryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}
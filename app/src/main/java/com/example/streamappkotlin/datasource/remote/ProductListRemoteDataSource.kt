package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.Product
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductListRemoteDataSource(private var apiService: ApiService) {

    fun getProductList(
        id: Int,
        offset: Int,
        observer: Observer<List<Product>>
    ) {
        val productListObservable = apiService.getProductList(id, 20, offset)
        productListObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}
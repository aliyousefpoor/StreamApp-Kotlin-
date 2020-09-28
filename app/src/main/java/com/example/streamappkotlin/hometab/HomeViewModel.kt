package com.example.streamappkotlin.hometab

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource
import com.example.streamappkotlin.model.Store
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomeViewModel(private var homeRemoteDataSource: HomeRemoteDataSource) : ViewModel() {
    private val TAG = "HomeViewModel"

    private var _storeListLiveData: MutableLiveData<Store> = MutableLiveData()
    var storeListLiveData = _storeListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    //    fun getStore() {
//        homeRemoteDataSource.getStore(object : DataSourceListener<Store> {
//            override fun onResponse(response: Store) {
//                _storeListLiveData.value = response
//                _errorLiveData.value = false
//                _loadingLiveData.value = false
//            }
//
//            override fun onFailure(throwable: Throwable?) {
//                _errorLiveData.value = true
//                _loadingLiveData.value = false
//            }
//
//        })
//    }


    fun rxGetStore() {

        homeRemoteDataSource.getStoreRx(object : Observer<Store> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: Store) {
                _storeListLiveData.value = t
                _loadingLiveData.value = false
                _errorLiveData.value = false
            }

            override fun onError(e: Throwable) {
                _loadingLiveData.value = false
                _errorLiveData.value = true
            }
        })
    }

    init {
//        getStore()
        rxGetStore()
    }
}
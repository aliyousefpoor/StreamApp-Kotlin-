package com.example.streamappkotlin.hometab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.HomeRemoteDataSource
import com.example.streamappkotlin.model.Store

class HomeViewModel(private var homeRemoteDataSource: HomeRemoteDataSource) : ViewModel() {


    private var _storeListLiveData: MutableLiveData<Store> = MutableLiveData()
    var storeListLiveData = _storeListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    fun getStore() {
        homeRemoteDataSource.getStore(object : DataSourceListener<Store> {
            override fun onResponse(response: Store) {
                _storeListLiveData.value = response
                _errorLiveData.value = false
                _loadingLiveData.value = false
            }

            override fun onFailure(throwable: Throwable) {
                _errorLiveData.value = true
                _loadingLiveData.value = false
            }

        })
    }

    init {
        getStore()
    }
}
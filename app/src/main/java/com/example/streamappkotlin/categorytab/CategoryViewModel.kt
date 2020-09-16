package com.example.streamappkotlin.categorytab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource
import com.example.streamappkotlin.model.Category

class CategoryViewModel(private var categoryRemoteDataSource: CategoryRemoteDataSource) :
    ViewModel() {

    private var _categoryList: MutableLiveData<List<Category>> = MutableLiveData()
    var categoryList = _categoryList

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    fun getCategory() {
        categoryRemoteDataSource.getCategory(object : DataSourceListener<List<Category>> {
            override fun onResponse(response: List<Category>) {
                _categoryList.value = response
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
        getCategory()
    }

}
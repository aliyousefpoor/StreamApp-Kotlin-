package com.example.streamappkotlin.categorytab

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.remote.CategoryRemoteDataSource
import com.example.streamappkotlin.model.Category
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class CategoryViewModel(private var categoryRemoteDataSource: CategoryRemoteDataSource) :
    ViewModel() {
    private  val TAG = "CategoryViewModel"
    private var _categoryList: MutableLiveData<List<Category>> = MutableLiveData()
    var categoryList = _categoryList

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    fun getCategory() {
        categoryRemoteDataSource.getCategory(object : Observer<List<Category>> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: List<Category>) {
                _categoryList.value = t
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
        getCategory()
    }
}
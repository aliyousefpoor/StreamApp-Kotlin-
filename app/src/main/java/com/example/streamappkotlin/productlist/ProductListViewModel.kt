package com.example.streamappkotlin.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.remote.ProductListRemoteDataSource
import com.example.streamappkotlin.model.Product
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class ProductListViewModel(private var productListRemoteDataSource: ProductListRemoteDataSource) :
    ViewModel() {
    private val TAG = "ProductListViewModel"
    var offset: Int = 0
    var categoryId: Int = 0
    var productLists = ArrayList<Product>()

    private var _productListLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    var productListLiveData: LiveData<List<Product>> = _productListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    fun loadData() {
        _loadingLiveData.value = true
        productListRemoteDataSource.getProductList(
            categoryId,
            offset,
            object : Observer<List<Product>> {

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: ")
                }

                override fun onNext(t: List<Product>) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = false
                    productLists.addAll(t)
                    _productListLiveData.value = productLists
                    offset += t.size
                }

                override fun onError(e: Throwable) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = true
                    _productListLiveData.value = null                }
            })

    }

    fun getFirstData() {
        if (productLists.size == 0) {
            loadData()
        }
    }

    fun setId(categoryId: Int) {
        this.categoryId = categoryId
    }
}
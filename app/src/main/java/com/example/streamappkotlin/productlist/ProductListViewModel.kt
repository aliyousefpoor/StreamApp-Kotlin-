package com.example.streamappkotlin.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.ProductListRemoteDataSource
import com.example.streamappkotlin.model.Product
import java.util.ArrayList

class ProductListViewModel(private var productListRemoteDataSource: ProductListRemoteDataSource) :
    ViewModel() {

    var offset: Int = 0
    var categoryId: Int = 0
    var productLists = ArrayList<Product>()

    private var _productListLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    var productListLiveData: LiveData<List<Product>> = _productListLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData = _errorLiveData

    fun setId(categoryId: Int) {
        this.categoryId = categoryId
    }

    fun getFirstData() {
        if (productLists.size == 0) {
            loadData()
        }
    }

    fun loadData() {
        productListRemoteDataSource.getProductList(
            categoryId,
            offset,
            object : DataSourceListener<List<Product>> {
                override fun onResponse(response: List<Product>) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = false
                    productLists.addAll(response)
                    _productListLiveData.value = productLists
                    offset += response.size
                }

                override fun onFailure(throwable: Throwable) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = true
                    _productListLiveData.value=null
                }
            })
    }
}
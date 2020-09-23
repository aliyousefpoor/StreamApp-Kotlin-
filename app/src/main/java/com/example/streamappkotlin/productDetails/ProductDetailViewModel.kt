package com.example.streamappkotlin.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.Product

class ProductDetailViewModel(private var productDetailRemoteDataSource: ProductDetailRemoteDataSource) :
    ViewModel() {
    var productId: Int = 0

    private var _productDetailLiveData: MutableLiveData<Product> = MutableLiveData()
    var productDetailLiveData: LiveData<Product> = _productDetailLiveData

    private var _productCommentLiveData: MutableLiveData<List<Comment>> = MutableLiveData()
    var productCommentLiveData: LiveData<List<Comment>> = _productCommentLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private var _errorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData: LiveData<Boolean> = _errorLiveData

    private fun getProductDetail() {
        _loadingLiveData.value = true
        productDetailRemoteDataSource.getProductDetails(productId,
            object : DataSourceListener<Product> {
                override fun onResponse(response: Product) {
                    _productDetailLiveData.value = response
                    _loadingLiveData.value = false
                    _errorLiveData.value = false
                }

                override fun onFailure(throwable: Throwable?) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = true
                }

            })
    }

    private fun getComment() {
        productDetailRemoteDataSource.getComment(productId,
            object : DataSourceListener<List<Comment>> {
                override fun onResponse(response: List<Comment>) {
                    _productCommentLiveData.value = response
                    _loadingLiveData.value = false
                    _errorLiveData.value = false
                }

                override fun onFailure(throwable: Throwable?) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = true
                }

            })
    }

    fun setId(productId: Int) {
        this.productId = productId
    }

    fun getProduct() {
        getProductDetail()
        getComment()
    }
}
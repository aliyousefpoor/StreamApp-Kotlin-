package com.example.streamappkotlin.productDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.remote.ProductDetailRemoteDataSource
import com.example.streamappkotlin.model.Comment
import com.example.streamappkotlin.model.Product
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ProductDetailViewModel(private var productDetailRemoteDataSource: ProductDetailRemoteDataSource) :
    ViewModel() {
    private val TAG = "ProductDetailViewModel"
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
            object : Observer<Product> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: ")
                }

                override fun onNext(t: Product) {
                    _productDetailLiveData.value = t
                    _loadingLiveData.value = false
                    _errorLiveData.value = false
                }

                override fun onError(e: Throwable) {
                    _loadingLiveData.value = false
                    _errorLiveData.value = true
                }

            })
    }

    private fun getComment() {
        productDetailRemoteDataSource.getComment(productId, object : Observer<List<Comment>> {
            override fun onComplete() {
                Log.d(TAG, "onComplete: ")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onNext(t: List<Comment>) {
                _productCommentLiveData.value = t
                _loadingLiveData.value = false
                _errorLiveData.value = false
            }

            override fun onError(e: Throwable) {
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
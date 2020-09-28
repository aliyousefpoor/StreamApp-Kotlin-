package com.example.streamappkotlin.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.SingleLiveEvent
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.database.IsLoginListener
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse
import com.example.streamappkotlin.repository.LoginRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginShareViewModel(private var loginRepository: LoginRepository) : ViewModel() {
    private val TAG = "LoginShareViewModel"
    lateinit var loginStepOneRequestBody: LoginStepOneRequest

    private var _loginStepOneLiveData: MutableLiveData<LoginStepOneResponse> = MutableLiveData()
    var loginStepOneLiveData: LiveData<LoginStepOneResponse> = _loginStepOneLiveData

    private var _loginStepTwoLiveData: MutableLiveData<LoginStepTwoResponse> = MutableLiveData()
    var loginStepTwoLiveData: LiveData<LoginStepTwoResponse> = _loginStepTwoLiveData

    private var _isLogin: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var isLogin: SingleLiveEvent<Boolean> = _isLogin

//    fun loginStepOne(loginStepOneRequest: LoginStepOneRequest) {
//        loginStepOneRequestBody = loginStepOneRequest
//        loginRepository.loginStepOne(loginStepOneRequest,
//            object : DataSourceListener<LoginStepOneResponse> {
//                override fun onResponse(response: LoginStepOneResponse) {
//                    _loginStepOneLiveData.value = response
//                }
//
//                override fun onFailure(throwable: Throwable?) {
//                    _loginStepOneLiveData.value = null
//                }
//
//            })
//    }

    fun rxLoginStepOne(loginStepOneRequest: LoginStepOneRequest) {
        loginStepOneRequestBody = loginStepOneRequest
        loginRepository.rxLoginStepOne(loginStepOneRequest,
            object : Observer<LoginStepOneResponse> {
                override fun onComplete() {
                    Log.d(TAG, "stepOne onComplete: ")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "stepOne onSubscribe: ")
                }

                override fun onNext(t: LoginStepOneResponse) {
                    _loginStepOneLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "stepOne onError: $e")
                }

            })
    }

//    fun loginStepTwo(loginStepTwoRequest: LoginStepTwoRequest) {
//        loginRepository.loginStepTwo(loginStepTwoRequest,
//            object : DataSourceListener<LoginStepTwoResponse> {
//                override fun onResponse(response: LoginStepTwoResponse) {
//                    _loginStepTwoLiveData.value = response
//                }
//
//                override fun onFailure(throwable: Throwable?) {
//                    _loginStepTwoLiveData.value = null
//                }
//
//            })
//    }

    fun rxLoginStepTwo(loginStepTwoRequest: LoginStepTwoRequest) {
        loginRepository.rxLoginStepTwo(loginStepTwoRequest,
            object : Observer<LoginStepTwoResponse> {
                override fun onComplete() {
                    Log.d(TAG, "stepTwo onComplete: ")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "stepTwo onSubscribe: ")
                }

                override fun onNext(t: LoginStepTwoResponse) {
                    _loginStepTwoLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "stepTwo onError: $e")                }

            })
    }

    fun isLogin() {
        loginRepository.isLogin(object : IsLoginListener {
            override fun isLogin(boolean: Boolean) {
                if (boolean) {
                    _isLogin.postValue(true)
                } else {
                    _isLogin.postValue(false)
                }
            }

        })
    }

}
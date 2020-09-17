package com.example.streamappkotlin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.database.IsLoginListener
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse
import com.example.streamappkotlin.repository.LoginRepository

class LoginShareViewModel(private var loginRepository: LoginRepository) : ViewModel() {
    lateinit var loginStepOneRequestBody: LoginStepOneRequest

    private var _loginStepOneLiveData: MutableLiveData<LoginStepOneResponse> = MutableLiveData()
    var loginStepOneLiveData: LiveData<LoginStepOneResponse> = _loginStepOneLiveData

    private var _loginStepTwoLiveData: MutableLiveData<LoginStepTwoResponse> = MutableLiveData()
    var loginStepTwoLiveData: LiveData<LoginStepTwoResponse> = _loginStepTwoLiveData

    private var _isLogin: MutableLiveData<Boolean> = MutableLiveData()
    var isLogin: LiveData<Boolean> = _isLogin

    fun loginStepOne(loginStepOneRequest: LoginStepOneRequest) {
        loginStepOneRequestBody = loginStepOneRequest
        loginRepository.loginStepOne(loginStepOneRequest,
            object : DataSourceListener<LoginStepOneResponse> {
                override fun onResponse(response: LoginStepOneResponse) {
                    _loginStepOneLiveData.value = response
                }

                override fun onFailure(throwable: Throwable?) {
                    _loginStepOneLiveData.value = null
                }

            })
    }

    fun loginStepTwo(loginStepTwoRequest: LoginStepTwoRequest) {
        loginRepository.loginStepTwo(loginStepTwoRequest,
            object : DataSourceListener<LoginStepTwoResponse> {
                override fun onResponse(response: LoginStepTwoResponse) {
                    _loginStepTwoLiveData.value = response
                }

                override fun onFailure(throwable: Throwable?) {
                    _loginStepTwoLiveData.value = null
                }

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
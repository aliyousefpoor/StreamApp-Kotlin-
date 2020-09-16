package com.example.streamappkotlin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import com.example.streamappkotlin.repository.LoginRepository

class LoginShareViewModel(private var loginRepository: LoginRepository) : ViewModel() {
    private lateinit var loginStepOneRequestBody: LoginStepOneRequest

    private var _loginStepOneLiveData: MutableLiveData<LoginStepOneResponse> = MutableLiveData()
    var loginStepOneLiveData: LiveData<LoginStepOneResponse> = _loginStepOneLiveData

    fun loginStepOne(loginStepOneRequest: LoginStepOneRequest) {
        loginStepOneRequestBody = loginStepOneRequest
        loginRepository.loginStepOne(loginStepOneRequest,
            object : DataSourceListener<LoginStepOneResponse> {
                override fun onResponse(response: LoginStepOneResponse) {
                    _loginStepOneLiveData.value = response
                }

                override fun onFailure(throwable: Throwable) {
                    _loginStepOneLiveData.value = null
                }

            })
    }

}
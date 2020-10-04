package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginStepOneRemoteDataSource(private var apiService: ApiService) {

    fun loginStepOne(
        loginStepOneRequest1: LoginStepOneRequest,
        observer: Observer<LoginStepOneResponse>
    ) {
        val loginStepOneRequest = LoginStepOneRequest(
            loginStepOneRequest1.mobile, loginStepOneRequest1.device_id,
            loginStepOneRequest1.device_model, loginStepOneRequest1.device_os
        )
        val loginStepOneObservable = apiService.loginStepOne(loginStepOneRequest)
        loginStepOneObservable.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

}


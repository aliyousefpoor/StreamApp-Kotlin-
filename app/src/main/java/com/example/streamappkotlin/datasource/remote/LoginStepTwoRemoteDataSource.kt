package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginStepTwoRemoteDataSource(private var apiService: ApiService) {

    fun loginStepTwo(
        loginStepTwoRequest1: LoginStepTwoRequest,
        observer: Observer<LoginStepTwoResponse>
    ) {
        val loginStepTwoRequest = LoginStepTwoRequest(
            loginStepTwoRequest1.mobile, loginStepTwoRequest1.device_id,
            loginStepTwoRequest1.verification_code
        )
        val loginStepTwoObservable = apiService.loginStepTwo(loginStepTwoRequest)
        loginStepTwoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
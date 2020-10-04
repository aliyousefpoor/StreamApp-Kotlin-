package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.locale.database.IsLoginListener
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.datasource.remote.LoginStepTwoRemoteDataSource
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginRepository(
    private var loginStepOneRemoteDataSource: LoginStepOneRemoteDataSource,
    private var loginStepTwoRemoteDataSource: LoginStepTwoRemoteDataSource,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {

    fun loginStepOne(
        loginStepOneRequest: LoginStepOneRequest,
        observer: Observer<LoginStepOneResponse>
    ) {
        loginStepOneRemoteDataSource.loginStepOne(loginStepOneRequest, observer)
    }

    fun loginStepTwo(
        loginStepTwoRequest: LoginStepTwoRequest, observer: Observer<LoginStepTwoResponse>

    ) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwoRequest,
            object : Observer<LoginStepTwoResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: LoginStepTwoResponse) {
                    observer.onNext(t)
                    loginUser(t)
                }

                override fun onError(e: Throwable) {
                    observer.onError(e)
                }


            })
    }

    fun loginUser(loginStepTwoResponse: LoginStepTwoResponse) {
        userLocaleDataSourceImp.loginUser(loginStepTwoResponse)
    }

    fun isLogin(isLoginListener: IsLoginListener) {
        userLocaleDataSourceImp.isLogin(isLoginListener)
    }
}
package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.locale.database.IsLoginListener
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.datasource.remote.LoginStepTwoRemoteDataSource
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse

class LoginRepository(
    private var loginStepOneRemoteDataSource: LoginStepOneRemoteDataSource,
    private var loginStepTwoRemoteDataSource: LoginStepTwoRemoteDataSource,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {

    fun loginStepOne(
        loginStepOneRequest: LoginStepOneRequest,
        dataSourceListener: DataSourceListener<LoginStepOneResponse>
    ) {
        loginStepOneRemoteDataSource.loginStepOne(loginStepOneRequest, dataSourceListener)
    }

    fun loginStepTwo(
        loginStepTwoRequest: LoginStepTwoRequest,
        dataSourceListener: DataSourceListener<LoginStepTwoResponse>
    ) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwoRequest,
            object : DataSourceListener<LoginStepTwoResponse> {
                override fun onResponse(response: LoginStepTwoResponse) {
                    dataSourceListener.onResponse(response)
                    loginUser(response)
                }

                override fun onFailure(throwable: Throwable?) {
                    dataSourceListener.onFailure(throwable)
                }

            })

    }

    fun loginUser(loginStepTwoResponse: LoginStepTwoResponse) {
        userLocaleDataSourceImp.loginUser(loginStepTwoResponse)
    }

    fun isLogin(isLoginListener: IsLoginListener){
        userLocaleDataSourceImp.isLogin(isLoginListener)
    }
}
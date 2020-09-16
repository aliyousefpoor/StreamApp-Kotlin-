package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.remote.LoginStepOneRemoteDataSource
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse

class LoginRepository(private var loginStepOneRemoteDataSource: LoginStepOneRemoteDataSource) {
    
    fun loginStepOne(
        loginStepOneRequest: LoginStepOneRequest,
        dataSourceListener: DataSourceListener<LoginStepOneResponse>
    ){
        loginStepOneRemoteDataSource.loginStepOne(loginStepOneRequest,dataSourceListener)
    }
}
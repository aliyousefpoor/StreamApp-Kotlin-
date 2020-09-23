package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepOneResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginStepOneRemoteDataSource(private var apiService: ApiService) {

    fun loginStepOne(loginStepOneRequest1: LoginStepOneRequest, dataSourceListener: DataSourceListener<LoginStepOneResponse>) {
        val loginStepOneRequest = LoginStepOneRequest(
            loginStepOneRequest1.mobile, loginStepOneRequest1.device_id
            , loginStepOneRequest1.device_model, loginStepOneRequest1.device_os
        )
        apiService.loginStepOne(loginStepOneRequest)
            .enqueue(object : Callback<LoginStepOneResponse> {

                override fun onResponse(
                    call: Call<LoginStepOneResponse>,
                    response: Response<LoginStepOneResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            dataSourceListener.onResponse(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<LoginStepOneResponse>, t: Throwable) {
                    dataSourceListener.onFailure(t)
                }
            })
    }

}


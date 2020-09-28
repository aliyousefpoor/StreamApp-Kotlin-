package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.LoginStepTwoRequest
import com.example.streamappkotlin.model.LoginStepTwoResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class LoginStepTwoRemoteDataSource(private var apiService: ApiService) {
//    fun loginStepTwo(
//        loginStepTwoRequest1: LoginStepTwoRequest,
//        dataSourceListener: DataSourceListener<LoginStepTwoResponse>
//    ) {
//        val loginStepTwoRequest = LoginStepTwoRequest(
//            loginStepTwoRequest1.mobile,
//            loginStepTwoRequest1.device_id,
//            loginStepTwoRequest1.verification_code
//        )
//        apiService.loginStepTwo(loginStepTwoRequest)
//            .enqueue(object : Callback<LoginStepTwoResponse> {
//                override fun onResponse(
//                    call: Call<LoginStepTwoResponse>,
//                    response: Response<LoginStepTwoResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        if (response.body() != null) {
//                            dataSourceListener.onResponse(response.body()!!)
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginStepTwoResponse>, t: Throwable) {
//                    dataSourceListener.onFailure(t)
//                }
//            })
//    }

    fun rxLoginStepTwo(
        loginStepTwoRequest1: LoginStepTwoRequest,
        observer: Observer<LoginStepTwoResponse>
    ) {
        val loginStepTwoRequest = LoginStepTwoRequest(
            loginStepTwoRequest1.mobile, loginStepTwoRequest1.device_id,
            loginStepTwoRequest1.verification_code
        )
        val loginStepTwoObservable = apiService.rxLoginStepTwo(loginStepTwoRequest)
        loginStepTwoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }
}
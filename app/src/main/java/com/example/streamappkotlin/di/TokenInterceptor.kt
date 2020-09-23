package com.example.streamappkotlin.di

import com.example.streamappkotlin.login.di.LoginModule
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor() : Interceptor {
    private var database = LoginModule.provideUserDatabase()
    private var userLocaleDataSource =
        LoginModule.provideUserLocaleDataSourceImp(database.userDao())

    override fun intercept(chain: Interceptor.Chain): Response {
        if (userLocaleDataSource.getTokenBlocking() != null) {
            val request: Request = newRequestWithAccessToken(
                chain.request(),
                userLocaleDataSource.getTokenBlocking()!!
            )
            return chain.proceed(request)
        } else {
            val request: Request = newRequestWithAccessToken(chain.request(), null)
            return chain.proceed(request)

        }
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String?): Request {
        if (accessToken != null) {
            return request.newBuilder().header("Authorization", "Token $accessToken").build()
        } else {
            return request.newBuilder().build()
        }
    }
}
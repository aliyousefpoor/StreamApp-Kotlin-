package com.example.streamappkotlin.datasource.locale

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.database.*
import com.example.streamappkotlin.model.LoginStepTwoResponse
import com.example.streamappkotlin.model.User

class UserLocaleDataSourceImp(private var userDao: UserDao) {

    fun saveUser(user: User) {
        val updateUserAsyncTask = UpdateUserAsyncTask(user, userDao)
        updateUserAsyncTask.execute()
    }

    fun loginUser(loginStepTwoResponse: LoginStepTwoResponse) {
        val loginAsyncTask = LoginAsyncTask(loginStepTwoResponse, userDao)
        loginAsyncTask.execute()
    }

    fun getUser(dataSourceListener: DataSourceListener<User>) {
        val getUserAsyncTask = GetUserAsyncTask(userDao, dataSourceListener)
        getUserAsyncTask.execute()
    }

    fun isLogin(isLoginListener: IsLoginListener) {
        getUser(object : DataSourceListener<User> {
            override fun onResponse(response: User) {
                isLoginListener.isLogin(true)
            }

            override fun onFailure(throwable: Throwable?) {
                isLoginListener.isLogin(false)
            }

        })

    }
}
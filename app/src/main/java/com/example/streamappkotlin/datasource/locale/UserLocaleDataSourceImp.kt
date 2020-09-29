package com.example.streamappkotlin.datasource.locale

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.database.*
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import com.example.streamappkotlin.model.LoginStepTwoResponse
import com.example.streamappkotlin.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserLocaleDataSourceImp(private var userDao: UserDao) {

    fun saveUser(user: User) {
        val userEntity =
            UserEntity(user.id, user.token, user.name, user.date, user.gender, user.avatar)
        userDao.updateUser(userEntity).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun loginUser(loginStepTwoResponse: LoginStepTwoResponse) {
        val userEntity =
            UserEntity(loginStepTwoResponse.user_id, loginStepTwoResponse.token, "", "", "")
        userDao.insertUser(userEntity).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe()
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

    fun getTokenBlocking(): String? {
        if (userDao.getUser() != null) {
            return userDao.getUser().token
        } else {
            return null
        }
    }

}
package com.example.streamappkotlin.datasource.locale


import android.util.Log
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.database.*
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import com.example.streamappkotlin.model.LoginStepTwoResponse
import com.example.streamappkotlin.model.User
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class UserLocaleDataSourceImp(private var userDao: UserDao) {
    private val TAG = "UserLocaleDataSourceImp"
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
        userDao.getUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<UserEntity> {
                override fun onSuccess(t: UserEntity) {
                    val user = User(t.userId, t.token, t.name, t.date, t.gender, t.avatar)
                    dataSourceListener.onResponse(user)
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: ")
                }

                override fun onError(e: Throwable) {
                    dataSourceListener.onFailure(e)
                }

            })
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
        return if (userDao.getAll() != null) {
            userDao.getAll().token
        } else {
            null
        }
    }

}
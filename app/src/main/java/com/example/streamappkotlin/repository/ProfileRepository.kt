package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.File

class ProfileRepository(
    private var userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {

    fun getProfile(observer: Observer<User>) {
        userLocaleDataSourceImp.getUser(object : DataSourceListener<User> {
            override fun onResponse(response: User) {
                observer.onNext(response)
                getProfile(response.token, observer)
            }

            override fun onFailure(throwable: Throwable?) {
                observer.onError(throwable!!)
            }

        })
    }

    fun updateProfile(user: User, observer: Observer<UpdateResponse>) {
//        userRemoteDataSourceImpl.updateProfile(user, object : DataSourceListener<UpdateResponse> {
//            override fun onResponse(response: UpdateResponse) {
//
//                user.name = response.data.nickname
//                user.date = response.data.date_of_birth
//                user.gender = response.data.gender
//                user.avatar = response.data.avatar
//                userLocaleDataSourceImp.saveUser(user)
//                dataSourceListener.onResponse(response)
//            }
//
//            override fun onFailure(throwable: Throwable?) {
//                dataSourceListener.onFailure(throwable)
//            }
//
//        })
        userRemoteDataSourceImpl.rxUpdateProfile(user, object : Observer<UpdateResponse> {
            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(t: UpdateResponse) {
                user.name = t.data.nickname
                user.date = t.data.date_of_birth
                user.gender = t.data.gender
                user.avatar = t.data.avatar
                userLocaleDataSourceImp.saveUser(user)
                observer.onNext(t)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }

        })
    }

    private fun getProfile(token: String, observer: Observer<User>) {
        userRemoteDataSourceImpl.rxGetProfile(token, object : Observer<User> {
            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(t: User) {
                val user = User()
                user.id = t.id
                user.token = t.token
                user.name = t.name
                user.date = t.date
                user.gender = t.gender
                user.avatar = t.avatar
                userLocaleDataSourceImp.saveUser(user)
                observer.onNext(user)
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
//            override fun onResponse(response: User) {
//                val user = User()
//                user.id = response.id
//                user.token = token
//                user.name = response.name
//                user.date = response.date
//                user.gender = response.gender
//                user.avatar = response.avatar
//                userLocaleDataSourceImp.saveUser(user)
//                dataSourceListener.onResponse(response)
//            }
//
//            override fun onFailure(throwable: Throwable?) {
//                dataSourceListener.onFailure(throwable)
//            }

        })
    }

    fun updateImage(file: File, observer: Observer<UpdateResponse>) {
        userRemoteDataSourceImpl.updateImage(file, observer)
    }
}
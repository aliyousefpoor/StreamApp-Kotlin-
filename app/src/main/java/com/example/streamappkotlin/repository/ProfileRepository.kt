package com.example.streamappkotlin.repository

import android.util.Log
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.io.File

class ProfileRepository(
    private var userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {
    private  val TAG = "ProfileRepository"
    fun getProfile(observer: Observer<User>) {
        userLocaleDataSourceImp.getUser(object : SingleObserver<User> {
            override fun onSuccess(t: User) {
                observer.onNext(t)
                getProfile(t.token, observer)
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        })
    }

    fun updateProfile(user: User, observer: Observer<UpdateResponse>) {
        userRemoteDataSourceImpl.updateProfile(user, object : Observer<UpdateResponse> {
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
        userRemoteDataSourceImpl.getProfile(token, object : Observer<User> {
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
        })
    }

    fun updateImage(file: File, observer: Observer<UpdateResponse>) {
        userRemoteDataSourceImpl.updateImage(file, observer)
    }
}
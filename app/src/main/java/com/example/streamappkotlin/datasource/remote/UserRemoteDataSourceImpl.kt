package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.model.ProfileResponse
import com.example.streamappkotlin.model.UpdateProfile
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserRemoteDataSourceImpl(private var apiService: ApiService) {

    fun updateProfile(user: User, observer: Observer<UpdateResponse>) {
        val updateProfile = UpdateProfile(user.name, user.date, user.gender)
        val updateProfileObservable = apiService.update(updateProfile)

        updateProfileObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    fun getProfile(token: String, observer: Observer<User>) {
        val rxGetUserObservable = apiService.getUser()
        rxGetUserObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProfileResponse> {
                override fun onComplete() {
                    observer.onComplete()
                }

                override fun onSubscribe(d: Disposable) {
                    observer.onSubscribe(d)
                }

                override fun onNext(t: ProfileResponse) {
                    val user = User(t.id, token, t.nickname, t.date_of_birth, t.gender, t.avatar)
                    observer.onNext(user)
                }

                override fun onError(e: Throwable) {
                    observer.onError(e)
                }

            })

    }

    fun updateImage(
        file: File,
        observer: Observer<UpdateResponse>
    ) {
        val requestFile = RequestBody.create(MediaType.get("multipart/form-data"), file)
        val requestImage = MultipartBody.Part.createFormData("avatar", file.name, requestFile)
        val updateImageObservable = apiService.updateImage(requestImage)
        updateImageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}
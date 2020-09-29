package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.ProfileResponse
import com.example.streamappkotlin.model.UpdateProfile
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserRemoteDataSourceImpl(private var apiService: ApiService) {

//    fun updateProfile(user: User, dataSourceListener: DataSourceListener<UpdateResponse>) {
//        val updateProfile = UpdateProfile(user.name, user.date, user.gender)
//
//        apiService.update(updateProfile)
//            .enqueue(object : Callback<UpdateResponse> {
//                override fun onResponse(
//                    call: Call<UpdateResponse>,
//                    response: Response<UpdateResponse>
//
//                ) {
//                    dataSourceListener.onResponse(response.body()!!)
//                }
//
//                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
//                    dataSourceListener.onFailure(t)
//                }
//            })
//    }

    fun rxUpdateProfile(user: User, observer: Observer<UpdateResponse>) {
        val updateProfile = UpdateProfile(user.name, user.date, user.gender)
        val updateProfileObservable = apiService.rxUpdate(updateProfile)

        updateProfileObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    fun rxGetProfile(token: String, observer: Observer<User>) {
//        apiService.getUser().enqueue(object : Callback<ProfileResponse> {
//
//            override fun onResponse(
//                call: Call<ProfileResponse>,
//                response: Response<ProfileResponse>
//            ) {
//                val user =
//                    User(
//                        response.body()!!.id,
//                        token,
//                        response.body()!!.nickname,
//                        response.body()!!.date_of_birth,
//                        response.body()!!.gender,
//                        response.body()!!.avatar
//                    )
//                dataSourceListener.onResponse(user)
//            }
//            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
//                dataSourceListener.onFailure(t)
//            }
//        })
        val rxGetUserObservable = apiService.rxGetUser()
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
        val updateImageObservable = apiService.rxUpdateImage(requestImage)
        updateImageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
//        apiService.updateImage(requestImage).enqueue(object : Callback<UpdateResponse> {
//            override fun onResponse(
//                call: Call<UpdateResponse>,
//                response: Response<UpdateResponse>
//            ) {
//                dataSourceListener.onResponse(response.body()!!)
//            }
//
//            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
//                dataSourceListener.onFailure(t)
//            }
//
//        })
    }
}
package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.ProfileResponse
import com.example.streamappkotlin.model.UpdateProfile
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserRemoteDataSourceImpl(private var apiService: ApiService) {

    fun updateProfile(user: User, dataSourceListener: DataSourceListener<UpdateResponse>) {
        val updateProfile = UpdateProfile(user.name, user.date, user.gender)

        apiService.update( updateProfile)
            .enqueue(object : Callback<UpdateResponse> {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>

                ) {
                    dataSourceListener.onResponse(response.body()!!)
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    dataSourceListener.onFailure(t)
                }
            })
    }

    fun getProfile(token: String, dataSourceListener: DataSourceListener<User>) {
        apiService.getUser().enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                val user =
                    User(
                        response.body()!!.id,
                        token,
                        response.body()!!.nickname,
                        response.body()!!.date_of_birth,
                        response.body()!!.gender,
                        response.body()!!.avatar
                    )

                dataSourceListener.onResponse(user)

            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }


        })
    }

    fun updateImage(
        file: File,
        dataSourceListener: DataSourceListener<UpdateResponse>
    ) {
        val requestFile = RequestBody.create(MediaType.get("multipart/form-data"), file)
        val requestImage = MultipartBody.Part.createFormData("avatar", file.name, requestFile)
        apiService.updateImage( requestImage).enqueue(object : Callback<UpdateResponse> {
            override fun onResponse(
                call: Call<UpdateResponse>,
                response: Response<UpdateResponse>
            ) {
                dataSourceListener.onResponse(response.body()!!)
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                dataSourceListener.onFailure(t)
            }

        })
    }
}
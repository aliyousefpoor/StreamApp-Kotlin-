package com.example.streamappkotlin.datasource.remote

import com.example.streamappkotlin.ApiService
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.UpdateProfile
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSourceImpl(private var apiService: ApiService) {

    fun updateProfile(user: User, dataSourceListener: DataSourceListener<UpdateResponse>) {
        var updateProfile = UpdateProfile(user.name, user.date, user.gender)

        apiService.update(updateProfile).enqueue(object : Callback<UpdateResponse> {
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
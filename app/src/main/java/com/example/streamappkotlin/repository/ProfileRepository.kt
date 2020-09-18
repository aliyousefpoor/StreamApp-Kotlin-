package com.example.streamappkotlin.repository

import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.datasource.locale.UserLocaleDataSourceImp
import com.example.streamappkotlin.datasource.remote.UserRemoteDataSourceImpl
import com.example.streamappkotlin.model.UpdateResponse
import com.example.streamappkotlin.model.User

class ProfileRepository(
    private var userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
    private var userLocaleDataSourceImp: UserLocaleDataSourceImp
) {

    fun getProfile(dataSourceListener: DataSourceListener<User>) {
        userLocaleDataSourceImp.getUser(object : DataSourceListener<User> {
            override fun onResponse(response: User) {
                dataSourceListener.onResponse(response)
                getProfile(response.token, dataSourceListener)
            }

            override fun onFailure(throwable: Throwable?) {
                dataSourceListener.onFailure(throwable)
            }

        })
    }

    fun updateProfile(user: User, dataSourceListener: DataSourceListener<UpdateResponse>) {
        userRemoteDataSourceImpl.updateProfile(user, object : DataSourceListener<UpdateResponse> {
            override fun onResponse(response: UpdateResponse) {

                user.name = response.data.nickname
                user.date = response.data.date_of_birth
                user.gender = response.data.gender
                user.avatar = response.data.avatar
                userLocaleDataSourceImp.saveUser(user)
                dataSourceListener.onResponse(response)
            }

            override fun onFailure(throwable: Throwable?) {
                dataSourceListener.onFailure(throwable)
            }

        })
    }

    private fun getProfile(token: String, dataSourceListener: DataSourceListener<User>) {
        userRemoteDataSourceImpl.getProfile(token, object : DataSourceListener<User> {
            override fun onResponse(response: User) {
                val user = User()
                user.id = response.id
                user.token = token
                user.name = response.name
                user.date = response.date
                user.gender = response.gender
                user.avatar = response.avatar
                userLocaleDataSourceImp.saveUser(user)
                dataSourceListener.onResponse(response)
            }

            override fun onFailure(throwable: Throwable?) {
                dataSourceListener.onFailure(throwable)
            }

        })
    }
}
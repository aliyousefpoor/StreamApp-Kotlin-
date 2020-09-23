package com.example.streamappkotlin.datasource.locale.database

import android.os.AsyncTask
import com.example.streamappkotlin.datasource.DataSourceListener
import com.example.streamappkotlin.model.User

class GetUserAsyncTask(
    private var userDao: UserDao,
    private var dataSourceListener: DataSourceListener<User>
) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        val userEntity = userDao.getUser()
        if (userEntity != null) {
            val user = User(
                userEntity.userId,
                userEntity.token,
                userEntity.name,
                userEntity.date,
                userEntity.gender,
                userEntity.avatar
            )
            dataSourceListener.onResponse(user)
        }
        else{
            dataSourceListener.onFailure(null)
        }
        return null
    }
}
package com.example.streamappkotlin.datasource.locale.database

import android.os.AsyncTask
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import com.example.streamappkotlin.model.User

class UpdateUserAsyncTask(private var user: User, private var userDao: UserDao) :
    AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        val userEntity =
            UserEntity(user.id, user.token, user.name, user.date, user.gender, user.avatar)
        userDao.updateUser(userEntity)
        return null
    }
}
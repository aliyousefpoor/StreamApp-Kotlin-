package com.example.streamappkotlin.datasource.locale.database

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import com.example.streamappkotlin.datasource.locale.model.UserLogin
import com.example.streamappkotlin.model.LoginStepTwoResponse

class LoginAsyncTask(
    private var loginStepTwoResponse: LoginStepTwoResponse,
    private var userDao: UserDao
) :
    AsyncTask<Void, Void, Void>() {
    private  val TAG = "LoginAsyncTask"
    override fun doInBackground(vararg params: Void?): Void? {
        val userEntity = UserEntity(loginStepTwoResponse.user_id, loginStepTwoResponse.token,"","","")

        userDao.insertUser(userEntity)
        Log.d(TAG, "doInBackground: " + userEntity.userId + "\n" + userEntity.token)

        return null
    }
}
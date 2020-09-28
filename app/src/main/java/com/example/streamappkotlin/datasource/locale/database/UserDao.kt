package com.example.streamappkotlin.datasource.locale.database

import androidx.room.*
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import com.example.streamappkotlin.model.LoginStepTwoResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY userId")
    fun getAll(): UserEntity

    @Query("SELECT * FROM user WHERE EXISTS (SELECT userId FROM user)")
    fun getUser(): UserEntity

    @Insert
    fun insertUser(userEntity: UserEntity):Completable

    @Update
    fun updateUser(userEntity: UserEntity)

    @Query("UPDATE user SET name=:nickName,date=:date,gender=:gender WHERE token=:token")
    fun updateProfile(nickName: String, token: String, date: String, gender: String)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}
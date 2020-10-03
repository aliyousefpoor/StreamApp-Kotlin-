package com.example.streamappkotlin.datasource.locale.database

import androidx.room.*
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import io.reactivex.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY userId")
    fun getAll(): UserEntity

    @Query("SELECT * FROM user WHERE EXISTS (SELECT userId FROM user)")
    fun getUser(): Single<UserEntity>

    @Insert
    fun insertUser(userEntity: UserEntity):Completable

    @Update
    fun updateUser(userEntity: UserEntity):Completable

    @Query("UPDATE user SET name=:nickName,date=:date,gender=:gender WHERE token=:token")
    fun updateProfile(nickName: String, token: String, date: String, gender: String)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}
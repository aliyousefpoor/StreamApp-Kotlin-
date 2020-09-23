package com.example.streamappkotlin.datasource.locale.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey var userId: Int = 0,
    @ColumnInfo(name = "token") var token: String = "",
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "date") var date: String = "",
    @ColumnInfo(name = "gender") var gender: String = "",
    @ColumnInfo(name = "avatar") var avatar: String = ""
)

package com.example.streamappkotlin.datasource.locale.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(@PrimaryKey val userId: Int,
                      @ColumnInfo(name = "token") val token:String,
                      @ColumnInfo(name = "name") val name: String,
                      @ColumnInfo(name = "date") val date: String,
                      @ColumnInfo(name = "gender") val gender: String,
                      @ColumnInfo(name = "avatar") val avatar: String
                )

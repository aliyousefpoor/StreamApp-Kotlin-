package com.example.streamappkotlin.datasource.locale.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.streamappkotlin.datasource.locale.model.UserEntity
import java.util.concurrent.locks.Lock

@Database(entities = [UserEntity::class], exportSchema = false, version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_Name: String = "user_db"

                private var instance: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, UserDatabase::class.java,
                    DB_Name
                ).fallbackToDestructiveMigration().build()
            }
            return instance as UserDatabase
        }
//        @Volatile private var instance: UserDatabase? = null
//        private val LOCK = Any()
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context, UserDatabase::class.java,
//            DB_Name
//        ).build()
    }
}
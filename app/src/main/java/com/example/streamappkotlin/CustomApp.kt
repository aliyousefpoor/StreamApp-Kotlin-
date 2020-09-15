package com.example.streamappkotlin

import android.app.Application
import android.content.Context
import com.example.streamappkotlin.di.AppModule

class CustomApp : Application() {
    val appModule = AppModule()


    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    companion object {
        private lateinit var context: Context

        lateinit var instance: CustomApp

        fun getContext(): Context {
            return context
        }
    }
}
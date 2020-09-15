package com.example.streamappkotlin

import android.app.Application
import android.content.Context
import com.example.streamappkotlin.di.AppModule

class CustomApp : Application() {
    private lateinit var appModule: AppModule
    private lateinit var context: Context
    private lateinit var instance: CustomApp

    fun getInstance(): CustomApp {
        return instance
    }

    fun getAppModule(): AppModule {
        return appModule
    }

    fun getContext(): Context {
        return context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }
}
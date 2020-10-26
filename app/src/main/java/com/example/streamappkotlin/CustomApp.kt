package com.example.streamappkotlin

import android.app.Application
import android.content.Context
import com.example.streamappkotlin.categorytab.di.categoryModules
import com.example.streamappkotlin.di.AppModule
import com.example.streamappkotlin.hometab.di.homeModules
import com.example.streamappkotlin.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApp : Application() {


    override fun onCreate() {
        super.onCreate()
//        instance = this
        context = applicationContext
        startKoin {
            androidLogger()
            androidContext(this@CustomApp)
            modules(listOf(networkModule, homeModules, categoryModules, loginModule))
        }

    }

    companion object {
        private lateinit var context: Context

        lateinit var instance: CustomApp

        fun getContext(): Context {
            return context
        }
    }
}
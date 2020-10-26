package com.example.streamappkotlin

import android.app.Application
import android.content.Context
import com.example.streamappkotlin.categorytab.di.categoryModules
import com.example.streamappkotlin.hometab.di.homeModules
import com.example.streamappkotlin.login.di.loginModule
import com.example.streamappkotlin.productlist.di.productModules
import com.example.streamappkotlin.profile.profileModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApp : Application() {


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidLogger()
            androidContext(this@CustomApp)
            koin.loadModules(
                listOf(
                    networkModule,
                    homeModules,
                    categoryModules,
                    loginModule,
                    profileModules,
                    productModules
                )
            )
            koin.createRootScope()
        }

    }

    companion object {
        private lateinit var context: Context

        fun getContext(): Context {
            return context
        }
    }
}
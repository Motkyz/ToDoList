package com.example.lesson7

import android.app.Application
import android.content.Context
import com.example.lesson7.di.AppComponent
import com.example.lesson7.di.DaggerAppComponent

class MainApp: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        super.onCreate()
    }

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> this.appComponent
        else -> this.applicationContext.appComponent
    }
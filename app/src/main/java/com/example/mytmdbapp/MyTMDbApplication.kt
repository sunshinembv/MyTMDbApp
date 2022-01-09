package com.example.mytmdbapp

import android.app.Application
import com.example.mytmdbapp.di.AppComponent
import com.example.mytmdbapp.di.DaggerAppComponent

class MyTMDbApplication : Application() {

    private var _appComponent: AppComponent? = null

    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.create()
    }
}
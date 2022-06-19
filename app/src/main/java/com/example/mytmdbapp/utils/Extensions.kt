package com.example.mytmdbapp.utils

import android.content.Context
import com.example.mytmdbapp.MyTMDbApplication
import com.example.mytmdbapp.di.AppComponent

private const val PERCENT = 10

val Context.appComponent: AppComponent
    get() = when (this) {
        is MyTMDbApplication -> appComponent
        else -> applicationContext.appComponent
    }

fun Double.toPercent(): Int {
    return (this * PERCENT).toInt()
}

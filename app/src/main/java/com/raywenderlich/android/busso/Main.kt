package com.raywenderlich.android.busso

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.di.ServiceLocator
import com.raywenderlich.android.busso.di.ServiceLocatorImpl

class Main : Application() {
    // 1
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        // 2
        serviceLocator = ServiceLocatorImpl(this)
    }
}

// 3
internal fun <A : Any> AppCompatActivity.lookUp(name: String): A =
    (applicationContext as Main).serviceLocator.lookUp(name)
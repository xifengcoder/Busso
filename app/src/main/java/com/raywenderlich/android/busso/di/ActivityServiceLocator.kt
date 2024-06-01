package com.raywenderlich.android.busso.di

import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.ui.navigation.NavigatorImpl

const val NAVIGATOR = "Navigator"

val activityServiceLocatorFactory: ServiceLocatorFactory<AppCompatActivity> =
    { activity: AppCompatActivity -> ActivityServiceLocator(activity) }

class ActivityServiceLocator(val activity: AppCompatActivity) : ServiceLocator {
    override fun <A : Any> lookUp(name: String): A =
        when (name) {
            // 4
            NAVIGATOR -> NavigatorImpl(activity)
            else -> throw IllegalArgumentException("No component lookup for the key: $name")
        } as A
}
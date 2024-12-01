package com.raywenderlich.android.busso.di

import android.app.Activity
import com.raywenderlich.android.busso.network.NetworkModule
import com.raywenderlich.android.busso.ui.view.busarrival.BusArrivalFragment
import com.raywenderlich.android.busso.ui.view.busstop.BusStopFragment
import com.raywenderlich.android.busso.ui.view.main.MainActivity
import com.raywenderlich.android.busso.ui.view.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// 1
@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    // 2
    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity) // HERE

    fun inject(fragment: BusStopFragment) // 2

    fun inject(fragment: BusArrivalFragment) // HERE

    // 1
    @Component.Factory // 1
    interface Factory {
        // 2
        fun create(@BindsInstance activity: Activity): AppComponent
    }
}
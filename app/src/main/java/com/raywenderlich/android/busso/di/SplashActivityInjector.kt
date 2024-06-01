package com.raywenderlich.android.busso.di

import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.busso.Main
import com.raywenderlich.android.busso.SplashActivity
import com.raywenderlich.android.busso.lookUp

object SplashActivityInjector : Injector<SplashActivity> {
    override fun inject(target: SplashActivity) {
        // 2
        val activityServiceLocator =
            target.lookUp<ServiceLocatorFactory<AppCompatActivity>>(ACTIVITY_LOCATOR_FACTORY)
                .invoke(target)
        // 3
        target.locationObservable = (target.application as Main).serviceLocator.lookUp(LOCATION_OBSERVABLE) // ERROR
        // 4
        target.navigator = activityServiceLocator.lookUp(NAVIGATOR) // ERROR
    }

}
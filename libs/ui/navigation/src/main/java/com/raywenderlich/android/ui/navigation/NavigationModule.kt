package com.raywenderlich.android.ui.navigation

import android.app.Activity
import com.raywenderlich.android.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @Provides
    @ActivityScope
    fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)
}
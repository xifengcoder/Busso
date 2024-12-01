package com.raywenderlich.android.busso.di

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import com.raywenderlich.android.busso.permission.GeoLocationPermissionCheckerImpl
import com.raywenderlich.android.busso.ui.view.busarrival.BusArrivalPresenter
import com.raywenderlich.android.busso.ui.view.busarrival.BusArrivalPresenterImpl
import com.raywenderlich.android.busso.ui.view.busarrival.BusArrivalViewBinder
import com.raywenderlich.android.busso.ui.view.busarrival.BusArrivalViewBinderImpl
import com.raywenderlich.android.busso.ui.view.busstop.BusStopListPresenter
import com.raywenderlich.android.busso.ui.view.busstop.BusStopListPresenterImpl
import com.raywenderlich.android.busso.ui.view.busstop.BusStopListViewBinder
import com.raywenderlich.android.busso.ui.view.busstop.BusStopListViewBinderImpl
import com.raywenderlich.android.busso.ui.view.main.MainPresenter
import com.raywenderlich.android.busso.ui.view.main.MainPresenterImpl
import com.raywenderlich.android.busso.ui.view.splash.SplashPresenter
import com.raywenderlich.android.busso.ui.view.splash.SplashPresenterImpl
import com.raywenderlich.android.busso.ui.view.splash.SplashViewBinder
import com.raywenderlich.android.busso.ui.view.splash.SplashViewBinderImpl
import com.raywenderlich.android.location.api.model.LocationEvent
import com.raywenderlich.android.location.rx.provideRxLocationObservable
import com.raywenderlich.android.ui.navigation.Navigator
import com.raywenderlich.android.ui.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

@Module(includes = [AppModule.Bindings::class])
class AppModule() {
    // 2
    @Module
    interface Bindings {
        //        // 3
        @Binds
        fun bindSplashPresenter(impl: SplashPresenterImpl): SplashPresenter

        //
//        // 4
        @Binds
        fun bindSplashViewBinder(impl: SplashViewBinderImpl): SplashViewBinder

        @Binds
        fun bindMainPresenter(impl: MainPresenterImpl): MainPresenter // HERE

        @Binds
        fun bindBusStopListViewBinder(impl: BusStopListViewBinderImpl): BusStopListViewBinder

        @Binds
        fun bindBusStopListPresenter(impl: BusStopListPresenterImpl): BusStopListPresenter

        @Binds
        fun bindBusStopListViewBinderListener(impl: BusStopListPresenterImpl): BusStopListViewBinder.BusStopItemSelectedListener

        @Binds
        fun bindBusArrivalPresenter(impl: BusArrivalPresenterImpl): BusArrivalPresenter

        @Binds
        fun bindBusArrivalViewBinder(impl: BusArrivalViewBinderImpl): BusArrivalViewBinder
    }

    // 2
    @Provides
    fun provideLocationObservable(activity: Activity): Observable<LocationEvent> {
        // 3
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 4
        val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(activity)
        // 5
        return provideRxLocationObservable(locationManager, geoLocationPermissionChecker)
    }

    @Provides
    fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)
}


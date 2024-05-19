package com.raywenderlich.android.busso.di

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.raywenderlich.android.busso.permission.GeoLocationPermissionCheckerImpl
import com.raywenderlich.android.location.rx.provideRxLocationObservable

const val LOCATION_OBSERVABLE = "LocationObservable"
const val ACTIVITY_LOCATOR_FACTORY = "ActivityLocatorFactory"

class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {

    // 2
    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    // 3
    private val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(context)

    // 4
    private val locationObservable =
        provideRxLocationObservable(locationManager, geoLocationPermissionChecker)

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("ServiceCast")
    override fun <A : Any> lookUp(name: String): A = when (name) {
        // 5
        LOCATION_OBSERVABLE -> locationObservable
        ACTIVITY_LOCATOR_FACTORY -> activityServiceLocatorFactory
        else -> throw IllegalArgumentException("No component lookup for the key: $name")
    } as A

}
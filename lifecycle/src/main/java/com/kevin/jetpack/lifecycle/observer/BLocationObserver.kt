package com.kevin.jetpack.lifecycle.observer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *@author Kevin  2021/4/29
 */
class BLocationObserver(private val context: Context) : LifecycleObserver {
    companion object {
        const val TAG = "KevinLocationObserver"
        const val DEBUG = true
    }

    lateinit var locationManager: LocationManager
    lateinit var myLocationListener: MyLocationListener

    //@SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startGetLocation() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        myLocationListener = MyLocationListener()
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000,
                1F,
                myLocationListener
            )
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopGetLocation() {
        locationManager.removeUpdates(myLocationListener)
    }

    private fun checkPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            if (DEBUG) Log.e(
                TAG,
                "startGetLocation: 维度:${location.latitude} 经度:${location.longitude}"
            )
        }
    }
}
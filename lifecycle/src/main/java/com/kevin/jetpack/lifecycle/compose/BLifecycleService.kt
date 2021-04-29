package com.kevin.jetpack.lifecycle.compose


import android.util.Log
import androidx.lifecycle.LifecycleService
import com.kevin.jetpack.lifecycle.observer.BLocationObserver

class BLifecycleService : LifecycleService() {
    companion object {
        private const val TAG = "KevinLifecycleService"
        private const val DEBUG = true
    }

    init {
        if (DEBUG) Log.e(TAG, "$TAG:init")
    }

    override fun onCreate() {
        super.onCreate()
        lifecycle.addObserver(BLocationObserver(this))
    }
}
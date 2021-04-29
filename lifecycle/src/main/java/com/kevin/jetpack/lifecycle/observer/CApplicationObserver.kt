package com.kevin.jetpack.lifecycle.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 *@author Kevin  2021/4/29
 */
class CApplicationObserver : LifecycleObserver {
    companion object {
        const val TAG = "CApplicationObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e(TAG, "onCreate: $TAG")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onStop() {
        Log.e(TAG, "onStop: $TAG")
    }
}
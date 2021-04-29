package com.kevin.jetpack.lifecycle.observer

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 *@author Kevin  2021/4/29
 */
class AChronometer(context: Context, attrs: AttributeSet) : Chronometer(context, attrs),
    LifecycleObserver {
    private var mElapsedTime: Long = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        base = (SystemClock.elapsedRealtime() - mElapsedTime)
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        mElapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }
}
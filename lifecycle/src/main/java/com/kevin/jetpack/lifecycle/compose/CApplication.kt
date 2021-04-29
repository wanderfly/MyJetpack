package com.kevin.jetpack.lifecycle.compose

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.kevin.jetpack.lifecycle.observer.CApplicationObserver

/**
 *@author Kevin  2021/4/29
 */
class CApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(CApplicationObserver())
    }
}
package com.kevin.databinding

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 *@author Kevin  2021/10/9
 */
class RememberMe : BaseObservable() {
    companion object {
        private const val TAG = "RememberMe"
    }

    private var flag: Boolean = false

    @Bindable
    fun getRememberMe(): Boolean {
        return flag
    }

    fun setRememberMe(value: Boolean) {
        Log.e(TAG, "setRememberMe: $value")
        if (flag != value) {
            flag = value
            notifyPropertyChanged(BR.rememberMe)
        }
    }
}
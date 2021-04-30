package com.kevin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *@author Kevin  2021/4/30
 */
class BViewModelLiveData : ViewModel() {
    private val mutableLiveData = MutableLiveData<Int>()

    init {
        mutableLiveData.value = 0
    }

    fun getCurrentSecond(): MutableLiveData<Int> {
        return mutableLiveData
    }


}
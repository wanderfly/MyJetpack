package com.kevin.retrofit.sample3.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.retrofit.sample3.executeHttp
import com.kevin.retrofit.sample3.netApi
import kotlinx.coroutines.launch

/**
 *@author Kevin  2022/2/11
 */
class AccountLoginModel : ViewModel() {
    private val mutableLiveData: MutableLiveData<String> = MutableLiveData<String>("result")

    /*init {
        mutableLiveData.value = ""
    }*/

    fun getResult(): MutableLiveData<String> {
        return mutableLiveData
    }

    fun login() {
        viewModelScope.launch {
            val result = executeHttp {
                netApi.loginAccount(userName = "xzhang", pwd = "123456", tenantCode = "10001")
            }
            mutableLiveData.postValue(result.getStatusStr())
        }
    }

    companion object {
        private const val TAG = "AccountLoginModel"
    }
}
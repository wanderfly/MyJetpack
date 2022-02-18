package com.kevin.retrofit.sample3.vm

import android.util.Log
import androidx.lifecycle.*
import com.kevin.retrofit.sample3.executeHttp
import com.kevin.retrofit.sample3.netApi
import kotlinx.coroutines.launch

/**
 *@author Kevin  2022/2/11
 */
class VMAccountLogin : ViewModel(), DefaultLifecycleObserver {
    private val mutableLiveData = MutableLiveData(MAccountLogin())

    override fun onCreate(owner: LifecycleOwner) {
        Log.e(TAG, "onCreate: nice")
        super.onCreate(owner)
        updateValue(newRepResult = "初始化完成")
    }

    /*init {
        mutableLiveData.value = ""
    }*/

    fun getResult(): MutableLiveData<MAccountLogin> {
        return mutableLiveData
    }

    private fun getValue(): MAccountLogin {
        return mutableLiveData.value!!
    }

    private fun updateValue(newBtnEnableState: Boolean = true, newRepResult: String) {
        //mutableLiveData.value?.btnLoginEnable = newBtnEnableState
        //mutableLiveData.value?.result = newRepResult

        getValue().btnLoginEnable = newBtnEnableState
        getValue().result = newRepResult
        mutableLiveData.value = getValue()

        //mutableLiveData.postValue(mutableLiveData.value)
        //mutableLiveData.value = MAccountLogin(newBtnEnableState, newRepResult)

        //mutableLiveData.value?.result = newRepResult
        //mutableLiveData.value?.btnLoginEnable = newBtnEnableState
        //mutableLiveData.value = mutableLiveData.value
    }


    fun login() {
        updateValue(false, "登录中")
        viewModelScope.launch {
            val result = executeHttp {
                netApi.loginAccount(userName = "xzhang", pwd = "123456", tenantCode = "10001")
            }
            updateValue(true, result.getStatusStr())

        }
    }

    companion object {
        private const val TAG = "VMAccountLogin"
    }
}

data class MAccountLogin(
    var btnLoginEnable: Boolean = true,
    var result: String = "Result",
)
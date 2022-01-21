package com.kevin.retrofit.sample3

import android.util.Log
import com.kevin.retrofit.sample3.bean.FxResponse
import com.kevin.retrofit.sample3.bean.response.Base64Code
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 *@author Kevin  2022/1/19
 */
object Sample3Builder {
    private const val TAG = "Sample3Builder"
    fun getImageCode() {
        CoroutineScope(Dispatchers.IO).launch {
            val a = async {
                kotlin.runCatching {
                    Log.e(TAG, "getImageCode: start++")
                    netApi.getImageCode()
                    //val imageCode: FxResponse<Base64Code> = netApi.getImageCode()
                    //Log.e(TAG, "getImageCode: ${Thread.currentThread().name}")
                    //Log.e(TAG, "getImageCode: ${imageCode.getData().toString()}")
                }.onSuccess {
                    Log.e(TAG, "getImageCode: success $it")
                }.onFailure {
                    Log.e(TAG, "getImageCode: failure $it")
                }
            }
            a.await()

        }


    }
}
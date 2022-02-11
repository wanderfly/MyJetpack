package com.kevin.retrofit.sample3

import android.util.Log
import com.kevin.retrofit.sample3.bean.*
import com.kevin.retrofit.sample3.bean.response.AccountLogin
import kotlinx.coroutines.*

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

    fun getImageCode2() {
        CoroutineScope(Dispatchers.IO).launch {
            val deferred = async {
                val result = executeHttp {
                    netApi.getImageCode()
                }
                Log.e(TAG, "getImageCode2: $result")
                Log.e(TAG, "getImageCode2: =========================")
                Log.e(TAG, "getImageCode2: FxEmptyResponse:${result is FxEmptyResponse}")
                Log.e(TAG, "getImageCode2: FxSuccessResponse:${result is FxSuccessResponse}")
                Log.e(TAG, "getImageCode2: FxFailedResponse:${result is FxFailedResponse}")
                Log.e(TAG, "getImageCode2: FxErrorResponse:${result is FxErrorResponse}")

            }
            deferred.await()
        }
    }

    fun accountLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            var loginResult: FxResponse<AccountLogin>? = null
            val deferred = async {
                loginResult = executeHttp {
                    netApi.loginAccount(userName = "xzhang", pwd = "123456", tenantCode = "10001")
                }
            }
            deferred.await()
            Log.e(TAG, "accountLogin 5: ${loginResult.toString()}")
        }
    }
}
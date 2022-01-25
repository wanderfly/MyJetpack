package com.kevin.retrofit.sample3

import com.kevin.retrofit.OkHttpClientUtil
import com.kevin.retrofit.sample3.bean.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author Kevin  2022/1/19
 */
private const val BaseUrl = "https://cloud.scfxyb.com/api/"

/**
 *实例化retrofit客户端
 **/
private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(OkHttpClientUtil.generateOkHttpClient(BaseUrl))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

/**
 *创建网络请求接口实例
 **/
val netApi: NetApi by lazy {
    retrofit.create(NetApi::class.java)
}

/**
 *执行网络请求(封装对异常信息的捕获)
 **/
suspend fun <T> executeHttp(block: suspend () -> FxResponse<T>): FxResponse<T> {
    runCatching {
        block.invoke()
    }.onSuccess { result ->
        return if (result.isOK())
            FxSuccessResponse(result.data)
        else
            FxFailedResponse(result.code, result.msg)
    }.onFailure { e ->
        return FxErrorResponse(e)
    }
    return FxEmptyResponse()
}




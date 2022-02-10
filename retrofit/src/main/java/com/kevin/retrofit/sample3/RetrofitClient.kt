package com.kevin.retrofit.sample3

import com.kevin.retrofit.OkHttpClientUtil
import com.kevin.retrofit.sample3.bean.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author Kevin  2022/1/19
 */
private const val BaseUrl = "https://cloud.scfxyb.com/api/"
private const val TAG = "RetrofitClient"

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
 *下面的方式本身没多大问题，但从内存上来说，会对每次网络请求返回的结果
 *再次创建新的对象，存在重复分配内存的现象.所以换用下面的方式
 *@see executeHttp
 **/
suspend fun <T> executeHttpOld(block: suspend () -> FxResponse<T>): FxResponse<T> {
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

suspend fun <T> executeHttp(block: suspend () -> FxResponse<T>): FxResponse<T> {
    runCatching {
        block.invoke()
    }.onSuccess { result ->
        return if (result.isOK())
        //result.setStatusCode(5)
            result.setStatusCode(FxRepCode.SUCCESS)
        else
            result.setStatusCode(FxRepCode.FAILED)
    }.onFailure { e ->
        return FxErrorResponse<T>(e).setStatusCode(FxRepCode.ERROR)
    }

    return FxEmptyResponse<T>().setStatusCode(FxRepCode.EMPTY)
}





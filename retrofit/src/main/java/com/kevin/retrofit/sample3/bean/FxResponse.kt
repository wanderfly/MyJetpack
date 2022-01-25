package com.kevin.retrofit.sample3.bean

import java.util.*

/**
 *@author Kevin  2022/1/17
 */
open class FxResponse<T>(
    open val code: String? = null,
    open val msg: String? = null,
    open val data: T? = null,
    open val error: Throwable? = null
) {
    fun isOK(): Boolean {
        return Objects.equals(code, CODE_SUCCESS) && Objects.equals(msg, MSG_SUCCESS)
    }

    override fun toString(): String {
        return "isOk:${isOK()} code:$code ,msg:$msg ,data:${data.toString()} ,error:${error.toString()}"
    }

    companion object {
        private const val CODE_SUCCESS = "200"
        private const val MSG_SUCCESS = "success"
    }
}

/**
 * 服务器 --> 未返回数据
 **/
class FxEmptyResponse<T> : FxResponse<T>()

/**
 * 服务器 --> 请求成功状态码
 **/
data class FxSuccessResponse<T>(override val data: T?) :
    FxResponse<T>(data = data)

/**
 * 服务器 --> 请求失败状态码
 **/
data class FxFailedResponse<T>(override val code: String?, override val msg: String?) :
    FxResponse<T>(code = code, msg = msg)

/**
 * 网络请求 --> 抛出错误信息
 **/
data class FxErrorResponse<T>(override val error: Throwable?) :
    FxResponse<T>(error = error)

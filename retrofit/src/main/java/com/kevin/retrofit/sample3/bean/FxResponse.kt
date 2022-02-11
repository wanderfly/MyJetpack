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
    private var statusCode: Int = FxRepCode.EMPTY

    fun isOK(): Boolean {
        return Objects.equals(code, CODE_SUCCESS) && Objects.equals(msg, MSG_SUCCESS)
    }

    fun setStatusCode(@FxRepCode statusCode: Int): FxResponse<T> {
        this@FxResponse.statusCode = statusCode
        return this
    }

    @FxRepCode
    fun getStatusCode(): Int {
        if (isOK())
            return FxRepCode.SUCCESS
        return statusCode
    }

    fun getStatusStr(): String {
        when (getStatusCode()) {
            FxRepCode.SUCCESS -> return Str_SUCCESS
            FxRepCode.FAILED -> return Str_FAILED
            FxRepCode.ERROR -> return Str_ERROR
            FxRepCode.EMPTY -> return Str_EMPTY
        }
        return Str_EMPTY
    }

    override fun toString(): String {
        return "statusStr:${getStatusStr()} code:$code ,msg:$msg ,data:${data.toString()} ,error:${error.toString()}"
    }

    companion object {
        private const val CODE_SUCCESS = "200"
        private const val MSG_SUCCESS = "success"
    }
}

// Kotlin 中使用该方式的注解时，传入非该指定的类型值时，编译器不会红色提示
// 不合乎常理，所以改用java方式的整形类型注解

/*@IntDef(
    FxRepCode.SUCCESS,
    FxRepCode.FAILED,
    FxRepCode.ERROR,
    FxRepCode.EMPTY
)
@Retention(AnnotationRetention.SOURCE)
annotation class FxRepCode {
    companion object {
        const val SUCCESS = 0
        const val FAILED = 1
        const val ERROR = 2
        const val EMPTY = 3
    }
}*/

private const val Str_SUCCESS = "Success"
private const val Str_FAILED = "Failed"
private const val Str_ERROR = "Error"
private const val Str_EMPTY = "Empty"

/**
 * 服务器 --> 未返回数据
 **/
open class FxEmptyResponse<T> : FxResponse<T>()

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

package com.kevin.retrofit.sample2

import java.util.*

/**
 *@author Kevin  2022/1/17
 */
class BaseResponse<T>(
    private val code: String?,
    private val msg: String?,
    private val data: T?
) {
    fun isOK(): Boolean {
        return Objects.equals(code, CODE_SUCCESS) && Objects.equals(msg, MSG_SUCCESS)
    }

    fun getCode(): String? = code

    fun getMsg(): String? = msg

    fun getData(): T? = data

    override fun toString(): String {
        return "isOk:${isOK()} code:$code ,msg:$msg ,data:${data.toString()}"
    }

    companion object {
        private const val CODE_SUCCESS = "200"
        private const val MSG_SUCCESS = "success"
    }
}
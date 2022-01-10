package com.kevin.retrofit.sample1

/**
 *@author Kevin  2022/1/7
 */
class BaseResponse<T> {
    val data: T? = null
    val errorCode: Int? = null
    val errorMsg: String? = null
    var exception: Exception? = null
}
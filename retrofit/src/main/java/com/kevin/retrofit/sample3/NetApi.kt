package com.kevin.retrofit.sample3

import com.kevin.retrofit.sample3.bean.FxResponse
import com.kevin.retrofit.sample3.bean.response.Base64Code
import retrofit2.http.GET

/**
 *@author Kevin  2022/1/18
 */
interface NetApi {
    /**
     * 获取登录时图片验证码
     * */
    @GET("auth/oauth/code/captcha")
    suspend fun getImageCode(): FxResponse<Base64Code>

    suspend fun LoginAccount(): FxResponse<Any>
}
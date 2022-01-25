package com.kevin.retrofit.sample3

import com.kevin.retrofit.sample3.bean.FxResponse
import com.kevin.retrofit.sample3.bean.response.AccountLogin
import com.kevin.retrofit.sample3.bean.response.Base64Code
import retrofit2.http.*

/**
 *@author Kevin  2022/1/18
 */
interface NetApi {


    companion object {
        //用于HTTP注解时，method参数的调用
        const val GET: String = "GET"
        const val POST: String = "POST"
        const val PUT: String = "PUT"
        const val DELETE: String = "DELETE"
        const val PATH: String = "PATH"
        const val HEAD: String = "HEAD"
        const val OPTIONS: String = "OPTIONS"
    }

    /**
     * 获取登录时图片验证码
     * */
    @GET("auth/oauth/code/captcha")
    suspend fun getImageCode(): FxResponse<Base64Code>

    /**
     * 商家账户登录
     * */
    @POST("auth/login")
    @FormUrlEncoded
    suspend fun loginAccount(
        @Field("clientType") clientType: String = "app",
        @Field("username") userName: String,
        @Field("password") pwd: String,
        @Field("tenantCode") tenantCode: String
    ): FxResponse<AccountLogin>


}
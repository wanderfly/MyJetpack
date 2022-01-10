package com.kevin.retrofit.sample1

import retrofit2.http.GET

/**
 *@author Kevin  2022/1/7
 */
interface ApiInterface {
    /**
     * 获取图片验证码
     * */
    @GET("/auth/oauth/code/captcha")
    suspend fun getListProject(): BaseResponse<ListProjectBean>


}
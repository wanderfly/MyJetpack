package com.kevin.retrofit.sample3

import com.kevin.retrofit.OkHttpClientUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author Kevin  2022/1/19
 */
const val BaseUrl = "https://cloud.scfxyb.com/api/"

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(OkHttpClientUtil.generateOkHttpClient(BaseUrl))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val netApi: NetApi by lazy {
    retrofit.create(NetApi::class.java)
}



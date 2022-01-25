package com.kevin.retrofit.sample3.bean.response

/**
 *@author Kevin  2022/1/25
 */
data class AccountLogin(
    val user_id: String?,
    val token_type: String?,
    val access_token: String?,
    val refresh_token: String?,
    val expires_in: String?,
    val license: String?
)
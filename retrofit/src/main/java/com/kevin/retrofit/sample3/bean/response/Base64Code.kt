package com.kevin.retrofit.sample3.bean.response

/**
 *@author Kevin  2022/1/18
 */
class Base64Code(
    private val img: String?,
    private val captcha_token: String?
) {
    override fun toString(): String {
        return "\nimg:$img, \ncaptcha_token:$captcha_token"
    }
}
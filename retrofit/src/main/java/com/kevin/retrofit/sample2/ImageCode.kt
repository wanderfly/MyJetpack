package com.kevin.retrofit.sample2

/**
 *@author Kevin  2022/1/17
 */
class ImageCode(
    private val img: String?,
    private val captcha_token: String?,
) {
    override fun toString(): String {
        return "\nimg:$img, \ncaptcha_token:$captcha_token"
    }
}

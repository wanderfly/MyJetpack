package com.kevin.retrofit.sample2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Kevin  2022/1/10
 */
public interface BlogService {
    //default Call<ResponseBody> getBlog() {
    //    return null;
    //}

    @GET("auth/oauth/code/captcha")
    Call<ResponseBody> getBlog();

    @GET(value = "auth/oauth/code/captcha/{id}")
    Call<ResponseBody> getBlog2(@Path("id") int id);
}

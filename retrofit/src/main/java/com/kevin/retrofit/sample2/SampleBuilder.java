package com.kevin.retrofit.sample2;

import android.util.Log;

import androidx.annotation.NonNull;

import com.kevin.retrofit.OkHttpClientUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Kevin  2022/1/10
 */
public class SampleBuilder {
    private static final String TAG = SampleBuilder.class.getSimpleName();
    public static final String BaseUrl = "https://cloud.scfxyb.com/api/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(OkHttpClientUtil.generateOkHttpClient(BaseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final BlogService blogService = retrofit.create(BlogService.class);


    public void getAsync() {
        Call<ResponseBody> callEnqueue = blogService.getBlog();
        callEnqueue.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e(TAG, "onResponse: " + Thread.currentThread().getName());
                ResponseBody body = response.body();
                if (body != null) {
                    try {
                        String value = new String(body.bytes());
                        Log.e(TAG, "onResponse: " + value);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    Log.e(TAG, "onResponse: body null");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + Thread.currentThread().getName() + t.toString());
            }
        });
    }

    public void getSync() {
        Call<ResponseBody> callEnqueue = blogService.getBlog();
        new Thread(() -> {
            try {
                Response<ResponseBody> response = callEnqueue.execute();
                ResponseBody body = response.body();
                Log.e(TAG, "getSync: " + Thread.currentThread().getName());
                if (body != null) {
                    String value = new String(body.bytes());
                    Log.e(TAG, "onResponse: " + value);
                } else
                    Log.e(TAG, "onResponse: body null");
            } catch (IOException ignore) {
            }
        }).start();
    }
}

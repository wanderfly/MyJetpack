package com.kevin.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kevin.retrofit.databinding.ActivityRetrofitBinding
import com.kevin.retrofit.sample1.ApiInterface
import com.kevin.retrofit.sample2.SampleBuilder
import com.kevin.retrofit.sample3.Sample3Builder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitActivity : AppCompatActivity() {

    val Api: ApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://cloud.scfxyb.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build().create(ApiInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            builder.addInterceptor(interceptor.apply {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return builder.build()
    }

    private val bingDing by lazy {
        ActivityRetrofitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_retrofit)
        setContentView(bingDing.root)
        bingDing.btnGet.run {
            //setOnClickListener { SampleBuilder().getAsync() }
            //setOnClickListener { SampleBuilder().getSync() }

            /*setOnClickListener {

                //OkHttpClientUtil.okHttpPostFormMobile(
                //    OkHttpClientUtil.mobile_url,
                //    "13168710033",
                //    "112233",
                //    MyCallBack()
                //)

                OkHttpClientUtil.okHttpGet(OkHttpClientUtil.image_code_url, MyCallBack())
            }*/

            //setOnClickListener { SampleBuilder().getImageCode() }
            //setOnClickListener { Sample3Builder.getImageCode() }
            setOnClickListener { Sample3Builder.getImageCode2() }
        }

        bingDing.btnAccountLogin.setOnClickListener { Sample3Builder.accountLogin() }

    }

    private class MyCallBack : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e(TAG, "onFailure: ")
        }

        override fun onResponse(call: Call, response: Response) {
            val body: ResponseBody? = response.body
            Log.e(TAG, "onResponse: $body")
            body?.let {
                val value = String(body.bytes())
                Log.e(TAG, "onResponse: $value")
            }

        }

        companion object {
            private const val TAG = "MyCallBack"
        }
    }
}
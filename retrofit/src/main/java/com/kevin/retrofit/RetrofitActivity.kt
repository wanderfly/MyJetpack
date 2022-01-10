package com.kevin.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.retrofit.databinding.ActivityRetrofitBinding
import com.kevin.retrofit.sample1.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    val bingDing by lazy {
        ActivityRetrofitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_retrofit)
        setContentView(bingDing.root)
    }
}
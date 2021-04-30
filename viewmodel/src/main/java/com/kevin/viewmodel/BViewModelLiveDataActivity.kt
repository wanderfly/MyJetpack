package com.kevin.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.util.*

/**
 * kotlin中匿名内部类引用外部类对象
 * this@BViewModelLiveDataActivity
 **/
class BViewModelLiveDataActivity : AppCompatActivity() {
    private lateinit var mBViewModelLiveData: BViewModelLiveData
    private lateinit var mTvData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bview_model_live_data)
        mTvData = findViewById(R.id.tv_view_model_live_data)
        mBViewModelLiveData =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                BViewModelLiveData::class.java
            )
        mTvData.text = mBViewModelLiveData.getCurrentSecond().value.toString()
        startTimer()
        mBViewModelLiveData.getCurrentSecond().observe(this) { i ->
            mTvData.text = i.toString()
        }
    }

    private fun startTimer() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                mBViewModelLiveData.getCurrentSecond().postValue(
                    mBViewModelLiveData.getCurrentSecond().value?.plus(
                        1
                    )
                )

                //MainThread
                /*mBViewModelLiveData.getCurrentSecond().observe(this@BViewModelLiveDataActivity) { i ->
                    mTvData.text = i.toString()
                }*/
            }
        }, 1000, 1000)
    }
}
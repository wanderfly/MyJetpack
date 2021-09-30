package com.kevin.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class AViewModelActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mTvNumber: TextView
    lateinit var mAViewMode: AViewMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aactivity)
        mTvNumber = findViewById(R.id.tv_number)
        findViewById<Button>(R.id.btn_number).setOnClickListener(this)
        //方法一:官方提供的viewModel新建示例方式
        mAViewMode =
                ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                        AViewMode::class.java
                )
        //方法二:通过构造函数示例
        //推荐官方工厂模式:会通过HashMap去重，避免出现一个viewModel中的多个对象在生命周期.以及会缓存viewModel对象
        //mAViewMode = AViewMode(application)
        mTvNumber.text = mAViewMode.number.toString()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_number) {
            mTvNumber.text = (++(mAViewMode.number)).toString()
        }
    }

}
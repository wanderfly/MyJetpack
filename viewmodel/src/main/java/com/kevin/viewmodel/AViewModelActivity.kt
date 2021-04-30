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
        mAViewMode =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                AViewMode::class.java
            )
        mTvNumber.text = mAViewMode.number.toString()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_number) {
            mTvNumber.text = (++(mAViewMode.number)).toString()
        }
    }

}
package com.kevin.jetpack.lifecycle.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.jetpack.lifecycle.R
import com.kevin.jetpack.lifecycle.observer.AChronometer

class ALifecycleActivity : AppCompatActivity() {
    private lateinit var mChronometer: AChronometer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kevin_lifecycle_activiy)
        mChronometer = findViewById(R.id.kevin_chronometer)
        lifecycle.addObserver(mChronometer)
    }
}
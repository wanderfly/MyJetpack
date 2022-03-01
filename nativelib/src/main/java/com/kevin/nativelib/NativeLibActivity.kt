package com.kevin.nativelib

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.nativelib.databinding.ActivityNativeLibBinding

class NativeLibActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityNativeLibBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.btnGet) {
            setOnClickListener { text = "" + NativeLib().stringFromJNI() }
        }
    }
}
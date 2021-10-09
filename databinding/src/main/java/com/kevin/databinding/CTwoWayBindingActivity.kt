package com.kevin.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kevin.databinding.databinding.ActivityCTwoWayBindingBinding

class CTwoWayBindingActivity : AppCompatActivity() {
    private lateinit var mDataBinding: ActivityCTwoWayBindingBinding
    private val mRememberMe: RememberMe by lazy { RememberMe() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_c_two_way_binding)
        mDataBinding.button.setOnClickListener { mRememberMe.setRememberMe(!mRememberMe.getRememberMe()) }
        mDataBinding.model = mRememberMe
    }
}
package com.kevin.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.kevin.databinding.databinding.ActivityADataBindingBinding

/**
 * 该功能位于子模块中，启用dataBinding需要配置
 * buildFeatures {
 * dataBinding = true
 * }，同时主module中也需要这样配置
 **/
class ADataBindingActivity : AppCompatActivity() {

    var tvName: TextView? = null
    lateinit var binding: ActivityADataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_data_binding)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_a_data_binding)
        binding.user = User(name = "Kevin", age = 18)
        //binding.root.


    }
}
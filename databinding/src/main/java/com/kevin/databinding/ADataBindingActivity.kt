package com.kevin.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kevin.databinding.databinding.ActivityADataBindingBinding

/**
 * 该功能位于子模块中，启用dataBinding需要配置
 * buildFeatures {
 * dataBinding = true
 * }，同时主module中也需要这样配置
 **/
class ADataBindingActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ADataBindingActivity"
    }

    var tvName: TextView? = null
    var tvName1: TextView? = null
    var tvName2: TextView? = null
    var tvName3: TextView? = null

    lateinit var binding: ActivityADataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Todo setContentView 方案一:通过DataBinding工具实例化()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a_data_binding)
        binding.user = User(name = "Kevin", age = 18,state = true)
        //setContentView(R.layout.activity_a_data_binding) //Todo 此时不需要调用该方法

        //Todo setContentView 方案二:通过ViewBinding方式实例化(其内部的实现还是调用DataBinding工具类)
        //binding = ActivityADataBindingBinding.inflate(layoutInflater)
        //binding.user = User(name = "Kevin", age = 18)
        //setContentView(binding.root) //Todo 此时需要调用该方法

        //Todo 推荐使用 方案一(更简洁)

        binding.tvUserAge.setOnClickListener {
            Log.e(TAG, "onCreate: 点击")
            //Toast.makeText(this, "age", Toast.LENGTH_SHORT).show()
        }

        with(binding) {
            tvUserAge.setOnClickListener {
                Toast.makeText(this@ADataBindingActivity, "age", Toast.LENGTH_SHORT).show()
            }
            tvUserName.setOnClickListener {
                Toast.makeText(this@ADataBindingActivity, "name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.kevin.myjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import com.kevin.coroutine.CoroutineActivity
import com.kevin.databinding.ADataBindingActivity
import com.kevin.databinding.BDataFragActivity
import com.kevin.jetpack.lifecycle.compose.ALifecycleActivity
import com.kevin.jetpack.lifecycle.compose.BLifecycleService
import com.kevin.viewmodel.AViewModelActivity
import com.kevin.viewmodel.BViewModelLiveDataActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "MainActivity"
        private const val DEBUG = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInfo(name = "Kevin", address = "China")
        getInfo("Tony", "USA")
        findViewAndClick(
            R.id.btn_lifecycle_test,
            R.id.btn_lifecycle_service,
            R.id.btn_aviewmodel,
            R.id.btn_b_view_model_live_data,
            R.id.btn_b_data_binding,
            R.id.btn_frag_data_binding,
            R.id.btn_coroutine
        )
    }

    private fun getInfo(name: String, address: String): String {
        return "$name:$address"
    }

    private fun toActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    private fun toService(cls: Class<*>) {
        startService(Intent(this, cls))
    }

    private fun findViewAndClick(@IdRes vararg ids: Int) {
        for (id in ids) {
            findViewById<View>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_lifecycle_test -> toActivity(ALifecycleActivity::class.java)
            R.id.btn_lifecycle_service -> toService(BLifecycleService::class.java)
            R.id.btn_aviewmodel -> toActivity(AViewModelActivity::class.java)
            R.id.btn_b_view_model_live_data -> toActivity(BViewModelLiveDataActivity::class.java)
            R.id.btn_b_data_binding -> toActivity(ADataBindingActivity::class.java)
            R.id.btn_frag_data_binding -> toActivity(BDataFragActivity::class.java)
            R.id.btn_coroutine -> toActivity(CoroutineActivity::class.java)
        }
    }
}
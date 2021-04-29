package com.kevin.myjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.jetpack.lifecycle.compose.ALifecycleActivity
import com.kevin.jetpack.lifecycle.compose.BLifecycleService
import com.kevin.viewmodel.AActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInfo(name = "Kevin", address = "China")
        getInfo("Tony", "USA")
        //toLifecycleTest()
        //toLifecycleService()
        toAViewModel()
    }

    private fun getInfo(name: String, address: String): String {
        return "$name:$address"
    }

    private fun toLifecycleTest() {
        val intent = Intent(this, ALifecycleActivity::class.java)
        startActivity(intent)
    }

    private fun toLifecycleService() {
        val intent = Intent(this, BLifecycleService::class.java)
        startService(intent)
    }

    private fun toAViewModel() {
        val intent = Intent(this, AActivity::class.java)
        startActivity(intent)
    }
    fun toActivity(){

    }

}
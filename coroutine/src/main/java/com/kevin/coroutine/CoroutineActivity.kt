package com.kevin.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        test()

        //runBlocking {  }
    }

    private fun test() {
        CoroutineScope(Dispatchers.Main).launch { getData() }
    }

    private suspend fun getData(): String {

        //GlobalScope.launch {
        //    val nice: String = ""
        //    Log.e(TAG, "getData: $nice")
        //}
        Log.e(TAG, "getData: start:" + System.currentTimeMillis())
        delay(2000)
        Log.e(TAG, "getData: end:" + System.currentTimeMillis())
        return "nice"
    }

    private suspend fun getName(): String {
        return withContext(Dispatchers.IO) {
            "key"
        }
    }
}
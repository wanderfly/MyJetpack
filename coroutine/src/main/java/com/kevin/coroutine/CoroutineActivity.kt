package com.kevin.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly
import java.lang.Exception

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CoroutineActivity"
    }

    private lateinit var mBtnGetData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        mBtnGetData = findViewById(R.id.btn_get_data)
        mBtnGetData.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                mBtnGetData.isEnabled = false
                mBtnGetData.text = "获取数据中，请稍等..."
                val dataValue: String = getData()
                mBtnGetData.text = dataValue
                mBtnGetData.isEnabled = true
                //val myName = ThisIsASingleton.name
                val myName = ThisIsASingleton.getAddress()
                mBtnGetData.text = myName
            }

            //MainScope().coroutineContext.cancel()
        }

        //test()

        //runBlocking {  }

        val number = Runtime.getRuntime().availableProcessors()
        Log.e(TAG, "onCreate: $number")

    }


    private suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            Log.e(TAG, "getData: start:" + System.currentTimeMillis())
            delay(5000)

            //将IO协程所对应的线程休眠，可以测试是否会休眠Main线程 --> 结果不会
            //try {
            //    Thread.sleep(5000)
            //} catch (e: Exception) {
            //}

            Log.e(TAG, "getData: end:" + System.currentTimeMillis())
            "nice"
        }
    }

    private suspend fun getName(): String {
        return withContext(Dispatchers.IO) {
            "key"
        }
    }

    private suspend fun getDataAsync(): String {
        return withContext(Dispatchers.IO) {

            ""
        }
    }

    @TestOnly
    private fun testLaunchAndAsync(): Unit {
        GlobalScope.launch {
            val job1 = launch {
                delay(200)
                Log.e(TAG, "testLaunchAndAsync: job1")
            }
            Log.e(TAG, "testLaunchAndAsync: job 测试顺序1")
            val job2 = async {
                delay(300)
                Log.e(TAG, "testLaunchAndAsync: job2")
            }
        }
    }


    private suspend fun getTime(): String {
        try {

        } catch (e: Exception) {

        }
        return ""
    }

    private suspend fun getDoc(url: String) = withContext(Dispatchers.IO) { "" }

    /*扩展函数*/
    private fun ThisIsASingleton.getAddress(): String {
        return "HongKong"
    }
}
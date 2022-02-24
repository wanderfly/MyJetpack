package com.kevin.binarystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kevin.binarystate.databinding.ActivityBinaryStateManagerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BinaryStateManagerActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "BinaryStateManager"
    }

    private val binding by lazy {
        ActivityBinaryStateManagerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Default).launch {
            Log.e(TAG, "onCreate: 当前线程:" + Thread.currentThread().name)
            sampleBinaryStatus()
        }
    }


    private fun sampleBinaryStatus() {
        val binaryState = BinaryState()
        binaryState.setTag(TAG)

        //MODE_A: 示例 1、2、3
        Log.e(TAG, "sampleBinaryStatus: MODE_A: 示例>>>>>>>>>>>>>>> Start")
        binaryState
            .setModeA()
            .printBinaryStatus()
            .addStatus_8()
            .printBinaryStatus()
            .removeStatus_1()
            .printBinaryStatus()

        //MODE_B: 示例 1、4、5、6
        Log.e(TAG, "sampleBinaryStatus: MODE_B: 示例>>>>>>>>>>>>>>> Start")
        binaryState.setModeB()
            .printBinaryStatus()
            .removeStatus_1()
            .printBinaryStatus()
            .addStatus_1()
            .printBinaryStatus()

        //MODE_C: 示例 1、7、8
        Log.e(TAG, "sampleBinaryStatus: MODE_C: 示例>>>>>>>>>>>>>>> Start")
        binaryState.setModeC()
            .printBinaryStatus()
            .addStatus_2()
            .printBinaryStatus()
            .addStatus_5()
            .printBinaryStatus()
            .removeStatus_1()
            .printBinaryStatus()
    }
}
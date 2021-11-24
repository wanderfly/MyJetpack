package com.kevin.myjetpack;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Kevin  2021/11/17
 */
public class TestThread extends Thread {
    private static final String TAG = "TestThread";
    private boolean isRun;
    private OutputStream outputStream;

    public TestThread() {
        isRun = true;
    }

    public void release() {
        isRun = false;
        if (isAlive())
            interrupt();
    }

    @Override
    public void run() {
        while (isRun) {
            try {
                Log.e(TAG, "run: 循环开始");
                try {
                    outputStream.close();
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "run: 休眠6000");
                sleep(6000);
                Log.e(TAG, "run: 休眠6000结束");
                Log.e(TAG, "run: 休眠4000");
                sleep(4000);
                Log.e(TAG, "run: 休眠4000结束");
            } catch (InterruptedException e) {
                Log.e(TAG, "run: " + e.toString());
            } finally {
                Log.e(TAG, "run: finally");
            }


        }
    }
}

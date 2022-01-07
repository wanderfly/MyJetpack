package com.kevin.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.AssertionError
import kotlin.system.measureTimeMillis

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
            //Todo Test 1
            /*CoroutineScope(Dispatchers.Main).launch {
                mBtnGetData.isEnabled = false
                mBtnGetData.text = "获取数据中，请稍等..."
                val dataValue: String = getData()
                mBtnGetData.text = dataValue
                mBtnGetData.isEnabled = true
                //val myName = ThisIsASingleton.name
                val myName = ThisIsASingleton.getAddress()
                mBtnGetData.text = myName
            }
            Log.e(TAG, "onCreate: 启动 协程获取数据")*/

            //Todo Test 2
            //MainScope().coroutineContext.cancel()

            //Todo Test 3
            /*CoroutineScope(Dispatchers.Main).launch {
                Log.e(TAG, "onCreate: 执行+++")
                delay(500)
                Log.e(TAG, "onCreate: 执行完毕")
            }
            Log.e(TAG, "onCreate: 非阻塞执行")
            Thread.sleep(1000)
            Log.e(TAG, "onCreate: 线程休眠后执行")
            CoroutineScope(Dispatchers.Main).launch {
                Log.e(TAG, "onCreate: 再次启动协程++")
            }
            Log.e(TAG, "onCreate: 准备再次启动一个协程？")*/

            //Todo Test 4
            /*runBlocking {
                 Log.e(TAG, "onCreate: start++")
                 Thread.sleep(100)
                 Log.e(TAG, "onCreate: 进入阻塞块")
                 delay(500)
                 Log.e(TAG, "onCreate: 阻塞块执行完成")
             }
             Log.e(TAG, "onCreate: 阻塞块下面代码")*/

            //Todo Test 5
            /*runBlocking {
                val time = measureTimeMillis {
                    val one = doOne()
                    val two = doTwo()
                    Log.e(TAG, "onCreate: time:${one + two}")
                }
                Log.e(TAG, "onCreate: 耗时:$time")
            }*/

            //Todo Test 6
            /*CoroutineScope(Dispatchers.Main).launch {
                val timeStart = System.currentTimeMillis()
                val task1 = withContext(Dispatchers.IO) {
                    Log.e(TAG, "onCreate: 执行IO任务1，当前线程名:${Thread.currentThread().name}")
                    delay(1000)
                    Log.e(TAG, "onCreate: IO任务1执行完成")
                    "sync one"
                }
                Log.e(TAG, "onCreate: 该日志介于 task1和task2之间")
                val task2 = withContext(Dispatchers.IO) {
                    Log.e(TAG, "onCreate: 执行IO任务2，当前线程名:${Thread.currentThread().name}")
                    delay(2000)
                    Log.e(TAG, "onCreate: IO任务2执行完成")
                    "sync two"
                }
                Log.e(
                    TAG,
                    "onCreate: task1=$task1,task2=$task2,执行两个任务耗时:${System.currentTimeMillis() - timeStart}"
                )
            }
            Log.e(TAG, "onCreate: ++++同步执行两个任务")*/

            //Todo Test 7
            /*CoroutineScope(Dispatchers.Main).launch {
                val timeStart = System.currentTimeMillis()
                val task1 = async(Dispatchers.IO) {
                    Log.e(TAG, "onCreate: 执行任务1，当前线程名:${Thread.currentThread().name}")
                    delay(1000)
                    Log.e(TAG, "onCreate: 任务1执行完成")
                    "async one"
                }
                Log.e(TAG, "onCreate: 该日志介于 task1和 task2 之间")
                val task2 = async(Dispatchers.IO) {
                    Log.e(TAG, "onCreate: 执行任务2，当前线程名:${Thread.currentThread().name}")
                    delay(2000)
                    Log.e(TAG, "onCreate: 任务2执行完成")
                    "async two"
                }
                Log.e(
                    TAG,
                    "onCreate: task1=${task1.await()},task2=${task2.await()},执行两个异步任务耗时:${System.currentTimeMillis() - timeStart}"
                )
            }
            Log.e(TAG, "onCreate: ++++异步执行两个任务")*/

            //Todo Test 8
            /*runBlocking {
                val job = launch {
                    Log.e(TAG, "onCreate: 启动一个协程")
                    delay(10000)
                    Log.e(TAG, "onCreate: 该协程执行完毕")
                }
                Log.e(TAG, "onCreate: 一秒后将取消协程")
                delay(1000)
                Log.e(TAG, "onCreate: 准备取消协程")
                job.cancel()
                Log.e(TAG, "onCreate: 执行协程取消")
            }*/

            //Todo Test 9
            /*runBlocking {
                //如果其中一个子协程失败，所有其它兄弟协程也会被取消
                coroutineScope {
                    val job1 = launch {
                        Log.e(TAG, "onCreate: launch")
                        delay(400)
                        Log.e(TAG, "onCreate: job1 执行完毕:" + Thread.currentThread().name)
                    }

                    val job2 = async {
                        Log.e(TAG, "onCreate: async")
                        delay(200)
                        Log.e(TAG, "onCreate: job2 执行完毕:" + Thread.currentThread().name)
                        //Todo 测试其中一个协程失败(页面会闪退)
                        //throw IllegalArgumentException()
                    }
                }
            }*/

            //Todo Test 10
            /*runBlocking {
                //如果其中一个子协程失败，不会影响其它兄弟协程
                supervisorScope {
                    val job1 = launch {
                        Log.e(TAG, "onCreate: launch")
                        delay(400)
                        Log.e(TAG, "onCreate: job1 执行完毕")
                    }

                    val job2 = async {
                        Log.e(TAG, "onCreate: async")
                        delay(200)
                        Log.e(TAG, "onCreate: job2 执行完毕")
                        //Todo 测试其中一个协程失败(页面不会闪退)
                        throw IllegalArgumentException()
                    }
                }
            }*/

            //Todo Test 11
            /*runBlocking {
                val scope = CoroutineScope(Dispatchers.Default)
                scope.launch {
                    Log.e(TAG, "onCreate: 协程1，运行,当前线程:${Thread.currentThread().name}")
                    delay(1000)
                    Log.e(TAG, "onCreate: 协程1，执行完成")
                }
                scope.launch {
                    Log.e(TAG, "onCreate: 协程2，运行,当前线程:${Thread.currentThread().name}")
                    delay(1000)
                    Log.e(TAG, "onCreate: 协程2，执行完成")
                }
                Log.e(TAG, "onCreate: 启动两个协程")
                delay(2000)
                Log.e(TAG, "onCreate: 挂起2秒后")
            }*/

            //Todo Test 12
            /*runBlocking {
                val startTime = System.currentTimeMillis()
                val job = launch(Dispatchers.Default) {
                    var nextPrintTime = startTime
                    var i = 0
                    //Todo 在循环时加上isActive则可以取消
                    //while (i < 5&& isActive) {
                    //Todo cpu 密集型运算不能被取消(死循环直到不满足条件结束)
                    while (i < 5) {
                        if (System.currentTimeMillis() >= nextPrintTime) {
                            Log.e(TAG, "onCreate: job:I'm sleeping ${i++}")
                            nextPrintTime += 500
                        }
                    }
                }
                Log.e(TAG, "onCreate: 将在1300ms取消")
                delay(1300)
                Log.e(TAG, "onCreate: I'm tired of waiting!")
                job.cancelAndJoin() //Todo
                Log.e(TAG, "onCreate: Now I can quit")
            }*/

            //Todo Test 13
            /*runBlocking {
                launch(Dispatchers.Default + CoroutineName("test")) {
                    Log.e(TAG, "onCreate: I'm working in thread ${Thread.currentThread().name}")
                }
            }*/

            //Todo Test 14
            /*runBlocking {
                val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
                //新的协程会将CoroutineScope作为父级
                val job = scope.launch {
                    Log.e(
                        TAG,
                        "onCreate: launch:${coroutineContext[Job]} 线程名:${Thread.currentThread().name}"
                    )
                    // 通过async 创建的新协程会将当前协程作为父级
                    val result = async {
                        Log.e(
                            TAG,
                            "onCreate: async:${coroutineContext[Job]} 线程名:${Thread.currentThread().name}"
                        )
                        "result"
                    }.await()
                }
                job.join()
            }*/

            //Todo Test 15
            /*runBlocking {
                val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    Log.e(TAG, "onCreate: Caught $throwable")
                }
                val scope = CoroutineScope(Job() + Dispatchers.Main + coroutineExceptionHandler)
                val job = scope.launch(Dispatchers.IO) {
                    Log.e(TAG, "onCreate: ${Thread.currentThread().name} ${coroutineContext.job} $coroutineContext")
                }
            }*/

            //Todo Test 16(非根协程抛出的异常总是会传播--运行错误)
            /*runBlocking {
                val scope = CoroutineScope(Job())
                val job = scope.launch {
                    async {
                        throw IllegalArgumentException()
                    }
                }
                job.join()
            }*/

            //Todo Test 17(通过指定SupervisorJob()某一协程抛出异常，其它异常不会取消，但是系统会--运行异常)
            /*runBlocking {
                val superVisor = CoroutineScope(SupervisorJob())
                val job1 = superVisor.launch {
                    delay(100)
                    Log.e(TAG, "onCreate: child 1")
                    throw IllegalArgumentException()
                }
                val job2 = superVisor.launch {
                    //delay(500)
                    //Log.e(TAG, "onCreate: child 2")
                    //throw IllegalArgumentException()
                    try {
                        delay(110)
                        Log.e(TAG, "onCreate: child 2 延迟")
                    } finally {
                        Log.e(TAG, "onCreate: child 2 finished")
                    }
                }
                joinAll(job1, job2)
            }*/

            //Todo Test 18
            /*runBlocking {
                supervisorScope {
                    val child = launch {
                        try {
                            Log.e(TAG, "onCreate: The child is sleeping")
                            delay(Long.MAX_VALUE)
                        } finally {
                            Log.e(TAG, "onCreate: The child is cancelled")
                        }
                    }
                    yield()
                    Log.e(TAG, "onCreate: Throwing an exception from the scope")
                    throw AssertionError()
                }
            }*/

            //Todo Test 19
            /*runBlocking {
                val handler = CoroutineExceptionHandler { _, throwable ->
                    Log.e(TAG, "onCreate: Caught $throwable")
                }
                val job = GlobalScope.launch(handler) {
                    throw AssertionError()
                }
                val deferred = GlobalScope.async(handler) {
                    throw ArithmeticException()
                }
                job.join()
                deferred.await()
            }*/

            //Todo Test 20
        }

        //test()

        //runBlocking {  }

        val number = Runtime.getRuntime().availableProcessors()
        Log.e(TAG, "onCreate: $number")

    }

    private suspend fun doOne(): Int {
        Log.e(TAG, "doOne: ")
        delay(2000)
        return 11
    }

    private suspend fun doTwo(): Int {
        Log.e(TAG, "doTwo: ")
        delay(1000)
        return 22
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
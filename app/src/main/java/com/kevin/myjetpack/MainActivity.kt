package com.kevin.myjetpack

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import com.kevin.binarystate.BinaryStateManagerActivity
import com.kevin.coroutine.CoroutineActivity
import com.kevin.databinding.ADataBindingActivity
import com.kevin.databinding.BDataFragActivity
import com.kevin.databinding.CTwoWayBindingActivity
import com.kevin.databinding.User
import com.kevin.jetpack.lifecycle.compose.ALifecycleActivity
import com.kevin.jetpack.lifecycle.compose.BLifecycleService
import com.kevin.nativelib.NativeLibActivity
import com.kevin.retrofit.RetrofitActivity
import com.kevin.viewmodel.AViewModelActivity
import com.kevin.viewmodel.BViewModelLiveDataActivity
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "MainActivity"
        private const val DEBUG = true
    }

    val runner: Runner by lazy { Runner("Tony") }

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
            R.id.btn_two_way_binding,
            R.id.btn_coroutine,
            R.id.btn_retrofit,
            R.id.btn_binary_state_manager,
            R.id.btn_native,
        )

        val user = User("Tom", 25, false).apply {
            name = "Kevin-Tom"
            age = 33
        }
        Log.e(TAG, "onCreate: $user")

        val boolean: Boolean? = null
        Log.e(TAG, "onCreate: $boolean")

        Demo.`is`()
        showOnBoard("牙膏", ::getDiscountWords)
        showOnBoard("电饭锅") { name: String, age: Int ->
            "姓名:${name} 年龄:${age}"
        }
        Log.e(TAG, "onCreate: ${getWords("空心菜")}")

        val str1 = "Jason"
        val str2 = "Jason"
        //val str2 = "jason".capitalize()
        Log.e(TAG, "onCreate: 内容否相等:${str1 == str2} 引用是否相等:${str1 === str2}") //后面的引用值比较也相等(字符串常量池)

        val number1 = "8.98".toIntOrNull()

        val result: String? = File("").takeIf { it.exists() && it.canRead() }?.readText()
        Log.e(TAG, "onCreate: $result")

        Runner("Kevin")

        testMutableList()
        testMutableMap()
        testDeconstruct()
        testDataPlus()

        Log.e(TAG, "onCreate: $animals")
        Log.e(TAG, "onCreate: $babies")

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
            //R.id.btn_lifecycle_test -> startThread()
            //R.id.btn_lifecycle_service -> releaseThread()
            R.id.btn_aviewmodel -> toActivity(AViewModelActivity::class.java)
            R.id.btn_b_view_model_live_data -> toActivity(BViewModelLiveDataActivity::class.java)
            R.id.btn_b_data_binding -> toActivity(ADataBindingActivity::class.java)
            R.id.btn_frag_data_binding -> toActivity(BDataFragActivity::class.java)
            R.id.btn_two_way_binding -> toActivity(CTwoWayBindingActivity::class.java)
            R.id.btn_coroutine -> toActivity(CoroutineActivity::class.java)
            R.id.btn_retrofit -> toActivity(RetrofitActivity::class.java)
            R.id.btn_binary_state_manager -> toActivity(BinaryStateManagerActivity::class.java)
            R.id.btn_native -> toActivity(NativeLibActivity::class.java)
        }
    }

    private var thread: TestThread? = null
    private fun startThread() {
        thread = TestThread()
        thread?.start()
    }

    private fun releaseThread() {
        thread?.release()
    }

    private fun getDiscountWords(goodsName: String, hour: Int): String {
        val currentYear = 2027
        return "${currentYear}年，双11${goodsName}促销倒计时:$hour 小时"
    }

    private fun showOnBoard(goodsName: String, getDiscountWords: (String, Int) -> String) {
        val hour: Int = (1..24).shuffled().last()
        Log.e(TAG, "showOnBoard: ${getDiscountWords(goodsName, hour)}")
    }

    private fun configDiscountWords(): (String) -> String {
        val currentYear = 2027
        var hour: Int = (1..24).shuffled().last()
        return { goodName: String ->
            hour += 20
            "${currentYear}年，双11${goodName}促销倒计时: $hour 小时"
        }
    }

    private val getWords = configDiscountWords()

    /**
     * 可变列表
     **/
    private fun testMutableList() {
        val mutableList: MutableList<String> = mutableListOf("Jason", "Jack", "Jacky")
        mutableList += "Jimmy" //运算符重载
        Log.e(TAG, "testMutableList: $mutableList")

        mutableList -= "Jason"
        Log.e(TAG, "testMutableList: $mutableList")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mutableList.removeIf {
                //it.contains("Jack")
                it == "Jack"
            }
            Log.e(TAG, "testMutableList: $mutableList")
        }
    }

    private fun testMutableMap() {
        val mutableMap: MutableMap<String, Int> =
            mutableMapOf("Jack" to 20, "Jason" to 30, "Jacky" to 40)

        mutableMap += "Jimmy" to 30
        Log.e(TAG, "testMutableMap: $mutableMap")
        mutableMap["Jimmy"] = 31
        Log.e(TAG, "testMutableMap: $mutableMap")
    }

    /**
     * 解构
     **/
    private fun testDeconstruct() {
        val mutableList: MutableList<String> = mutableListOf("Jack", "Kevin", "Tony")
        val (origin: String, dest: String, proxy: String) = mutableList
        //val (origin: String, _: String, proxy: String) = mutableList
        Log.e(TAG, "testDeconstruct: $origin $dest $proxy")
    }

    private fun testDataPlus() {
        val c1 = Coordinate(10, 20)
        val c2 = Coordinate(23, 52)
        Log.e(TAG, "testDataPlus: ${c1 + c2}")
    }

    class Player {
        //Kotlin会默认为属性生成setter、getter方法，可以通过下面方式更改
        var name: String = "Jack"
            get() = field.capitalize()
            set(value) {
                field = value.trim()
            }
        val rolledValue: Int get() = (1..6).shuffled().first()
    }

    class Runner(name: String, var age: Int, var isNormal: Boolean) {
        constructor(name: String) : this(name, age = 10, isNormal = false)
    }

    data class Coordinate(var x: Int, var y: Int) {
        operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
    }

    interface Consumer<in T> {
        fun consume(item: T)
    }
    //out 子类泛型对象可以赋值给父类泛型对象
    //in  父类泛型对象可以赋值给子类泛型对象

    //fun <T> T.easyPrint(): T {
    //    Log.e(TAG, "easyPrint: $this")
    //    return this
    //}
    fun <T> T.easyPrint() = print(this)

    //扩展函数里面自带了this的隐士调用
    fun String.addExt() = "!".repeat(this.count())
    //fun String.addExt() = "!".repeat(count())

    private val animals: List<String> = listOf("zebra", "giraffe", "elephant", "rat")
    val babies: List<String> = animals
        .map { animal -> "a baby $animal" }
        .map { baby -> "$baby,with the cutest little tail ever !" }


}
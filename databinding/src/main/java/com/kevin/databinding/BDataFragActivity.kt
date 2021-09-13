package com.kevin.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class BDataFragActivity : AppCompatActivity() {
    lateinit var mFragManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_data_frag)
        mFragManager = supportFragmentManager
        addFrag(BlankFragment())
        //addFrag(BlankFragment.newInstance("",""))
    }

    private fun addFrag(frag: Fragment) {
        mFragManager.beginTransaction().add(R.id.frag_container, frag).commitAllowingStateLoss()
    }
}
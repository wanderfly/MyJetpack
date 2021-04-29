package com.kevin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 *@author Kevin  2021/4/29
 */
class AViewMode(application: Application) : AndroidViewModel(application) {
    var number: Int = 0

}
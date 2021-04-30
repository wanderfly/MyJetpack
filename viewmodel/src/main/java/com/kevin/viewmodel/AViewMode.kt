package com.kevin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 *@author Kevin  2021/4/29
 * Note:
 * 1.ViewModel 中不要传入Context,会导致内存泄漏
 * 2.如果要使用Context,请使用AndroidViewModel中的Application
 */
class AViewMode(application: Application) : AndroidViewModel(application) {
    var number: Int = 0

}
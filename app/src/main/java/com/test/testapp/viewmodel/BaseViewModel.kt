package com.test.testapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Created By Tharindu on 7/8/2019
 *
 */
open class BaseViewModel : AndroidViewModel {

    constructor(application: Application) : super(application)

    var onError: MutableLiveData<Throwable> = MutableLiveData()
    var showProgress: MutableLiveData<Boolean> = MutableLiveData()

    protected fun onError(err: Throwable) {
        showProgress.value = false
        onError.value = err
    }
}
package com.test.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Tharindu on 7/8/2019
 *
 */
open class BaseViewModel : ViewModel() {

    var onError: MutableLiveData<Throwable> = MutableLiveData()
    var showProgress : MutableLiveData<Boolean> = MutableLiveData()

    protected fun onError(err: Throwable) {
        onError.value = err
    }
}
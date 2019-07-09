package com.test.testapp.feature

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.idling.CountingIdlingResource

open class BaseActivity : AppCompatActivity() {

    val countingIdlingResource: CountingIdlingResource = CountingIdlingResource("API_LOADING")

    /**
     * show Progress Bar
     */
    fun showProgress() {
        countingIdlingResource.increment()
        Log.e("PROGRESS", "SHOW")
    }

    /**
     * hide Progress Bar
     */
    fun hideProgress() {
        countingIdlingResource.decrement()
        Log.e("PROGRESS", "HIDE")
    }
}

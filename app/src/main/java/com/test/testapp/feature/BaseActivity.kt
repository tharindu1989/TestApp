package com.test.testapp.feature

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.idling.CountingIdlingResource
import com.test.testapp.feature.component.ProgressDialog

open class BaseActivity : AppCompatActivity() {

    val countingIdlingResource: CountingIdlingResource = CountingIdlingResource("API_LOADING")
    protected var progress: ProgressDialog? = null

    /**
     * show Progress Bar
     */
    fun showProgress() {
        countingIdlingResource.increment()
        progress?.show()
    }

    /**
     * hide Progress Bar
     */
    fun hideProgress() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
        progress?.hide()
        Log.e("PROGRESS", "HIDE")
    }
}

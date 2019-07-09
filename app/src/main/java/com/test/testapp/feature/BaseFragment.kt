package com.test.testapp.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.testapp.feature.main.MainActivity

/**
 * Created By Tharindu on 7/8/2019
 *
 */
open class BaseFragment : Fragment() {

    var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivity = activity as? MainActivity
    }

    /**
     * show Error Toast
     */
    protected fun showError(error: String?) {
        mActivity?.showError(error)
    }
}
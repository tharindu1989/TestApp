package com.test.testapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert

/**
 * Created By Tharindu on 7/9/2019
 * check Recycler view have items
 */
class RecyclerViewItemCountAssertion : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        val rvView: RecyclerView? = view as? RecyclerView
        val itemSize = rvView?.adapter?.itemCount ?: 0
        Assert.assertNotEquals(itemSize, 0)
    }
}
package com.test.testapp

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

/**
 * Created By Tharindu on 7/9/2019
 *
 */
class CustomViewAction {

    fun clickChildView(id: Int): ViewAction {

        return object : ViewAction {
            override fun getDescription(): String {
                return "Click Item by Child View"
            }

            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun perform(uiController: UiController?, view: View?) {
                val childView = view?.findViewById<View>(id)
                childView?.performClick()
            }
        }
    }
}
package com.test.testapp.feature.main

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.testapp.R
import com.test.testapp.feature.BaseActivity
import com.test.testapp.feature.list.ListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val fragmentStack: MutableList<String> = mutableListOf()
    private var stackId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        pushFragment(ListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Push Detail ot Fragment Stack
     */
    private fun pushStack(item: String) = fragmentStack.add(item)

    /**
     * remove item from Fragment Stack
     */
    private fun popStack(): String? {
        val item = fragmentStack.lastOrNull()
        if (!fragmentStack.isEmpty()) {
            fragmentStack.removeAt(fragmentStack.size - 1)
        }
        return item
    }

    /**
     * Remove Fragment From Stack
     * @param numberOfTimes : Number of Fragment to remove
     */
    fun popFragment(numberOfTimes: Int = 1) {
        val fragmentManager = this.supportFragmentManager
        for (item in 1..numberOfTimes) {
            popStack()?.let {
                fragmentManager.popBackStack(it, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } ?: run {
                this.finish()
            }
        }
    }

    /**
     * Add Fragment to Stack
     * @param fragment : Fragment to Add
     * @param bundleData : Fragment Data
     */
    fun pushFragment(
        fragment: Fragment,
        bundleData: Bundle? = null
    ) {
        bundleData?.let {
            fragment.arguments = it
        }
        val fragmentManager = this.supportFragmentManager
        val ft = fragmentManager?.beginTransaction()
        val tagName = "${stackId}_TAG"
        stackId++
        ft?.add(R.id.container, fragment, tagName)
        ft?.addToBackStack(tagName)
        pushStack(tagName)
        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft?.commit()
    }

    /**
     * show Error Message
     * @param error : Error Message to Display
     */
    fun showError(error: String? = null) {

        var snackBar = Snackbar.make(
            findViewById(R.id.coordinatorLayout),
            R.string.network_error, Snackbar.LENGTH_SHORT
        )
        snackBar.view.setBackgroundColor(resources.getColor(R.color.red))

        error?.let {
            snackBar.setText(it)
        }
        snackBar.show()

    }

}

package com.test.testapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.test.testapp.feature.list.viewholder.DetailViewHolder
import com.test.testapp.feature.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created By Tharindu on 7/9/2019
 *
 */
@RunWith(AndroidJUnit4::class)
class CountryDetailsListTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testCountryDetailList() {

        IdlingRegistry.getInstance().register(activityTestRule.activity.countingIdlingResource)
        onView(withId(R.id.countryDetailsRv)).check(RecyclerViewItemCountAssertion())
    }

    @Test
    fun testItemClick() {

        IdlingRegistry.getInstance().register(activityTestRule.activity.countingIdlingResource)
        onView(withId(R.id.countryDetailsRv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<DetailViewHolder>(
                0,
                CustomViewAction().clickChildView(R.id.countryImg)
            )
        )
        onView(withId(R.id.detailsViewPager)).check(matches(isDisplayed()))
    }
}
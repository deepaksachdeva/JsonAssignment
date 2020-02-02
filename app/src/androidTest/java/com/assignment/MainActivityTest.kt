package com.assignment

import androidx.test.rule.ActivityTestRule
import org.junit.Test
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.junit.Rule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions
import com.assignment.adapter.RecyclerViewAdapter
import androidx.test.espresso.matcher.RootMatchers
import org.hamcrest.Matchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        true,
        true
    )

    @Test
    fun testSampleRecyclerVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCaseForRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerViewAdapter.ViewHolder>(0, click()))
    }

    @Test
    fun testCaseForRecyclerScroll() {
        // Get total item of RecyclerView
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount = recyclerView.adapter!!.itemCount

        // Scroll to end of page with position
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(activityRule.activity.window.decorView)
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerViewAdapter.ViewHolder>(itemCount - 1))
    }
}

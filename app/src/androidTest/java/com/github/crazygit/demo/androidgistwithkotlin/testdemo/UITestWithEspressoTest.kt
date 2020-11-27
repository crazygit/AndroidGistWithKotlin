package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.crazygit.demo.androidgistwithkotlin.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UITestWithEspressoTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<UITestWithEspresso> =
        ActivityScenarioRule(UITestWithEspresso::class.java)


    @Test
    fun test_textview_exist() {
        onView(withId(R.id.incrementButton)).perform(click())
        onView(withId(R.id.result))
            .check(matches(withText("1")))
    }
}
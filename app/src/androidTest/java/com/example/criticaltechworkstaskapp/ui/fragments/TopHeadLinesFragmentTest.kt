package com.example.criticaltechworkstaskapp.ui.fragments

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.presentation.adapter.NewsAdapter
import com.example.criticaltechworkstaskapp.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class TopHeadLinesFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp() {
        hiltRule.inject()

    }


    @Test
    fun confirmWelcomeMarqueeTextandHeadlinesRvViewsAreDisplayed() {

        val scenario = launchFragmentInHiltContainer<TopHeadLinesFragment>()

        //wait for 3 seconds for network call then check recyclerview is displayed
        runBlocking {
            delay(3000)
        }

        onView(withId(R.id.welcome_text_marquee)).check(ViewAssertions.matches(withText(R.string.welcome_text)))

        onView(withId(R.id.rv_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))



    }

}
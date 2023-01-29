package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import com.example.criticaltechworkstaskapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.model.Source
import org.junit.runner.RunWith

//import org.junit.jupiter.api.Assertions.*

@RunWith(AndroidJUnit4::class)
internal class HeadLineDetailsFragmentTest{


    @Before
    fun setUp() {

        launchFragmentInContainer<HeadLineDetailsFragment>(fragmentArgs = null, factory = null)
    }

    @Test
    fun confirmViewsWithImageTitleDesandContentAreDisplayed() {

      onView(withId(R.id.image_detail_large))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
       onView(withId(R.id.dtitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
       onView(withId(R.id.ddescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.dcontent))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


}
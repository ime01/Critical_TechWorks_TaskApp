package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import com.example.criticaltechworkstaskapp.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.criticaltechworkstaskapp.common.formatDateTime
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.model.Source
import org.junit.runner.RunWith

//import org.junit.jupiter.api.Assertions.*

@RunWith(AndroidJUnit4::class)
internal class HeadLineDetailsFragmentTest{

    val news = News(
        author = "JOHN WICK",
        content = "JOHN WICK SPOTTED IS BACK FIGHTING HIS WAY OUT",
        description = "JOHN WICK SPOTTED",
        publishedAt = "29/01/2023",
        source = Source("cnn", "CNN News"),
        title = "JOHN WICK",
        urlToImage = ""
    )


    @Before
    fun setUp() {

        launchFragmentInContainer<HeadLineDetailsFragment>(fragmentArgs =  bundleOf(
            "newsModel" to  news,
        ), factory = null)
    }

    @Test
    fun confirmViewsCorrectNewsDataPassedtoDetailsScreenIsDisplayed() {

        onView(withId(R.id.dtitle)).check(ViewAssertions.matches(
            ViewMatchers.withText(news.title)
        ))

        onView(withId(R.id.ddescription)).check(ViewAssertions.matches(
            ViewMatchers.withText(news.description)
        ))

        onView(withId(R.id.dcontent)).check(ViewAssertions.matches(
            ViewMatchers.withText(news.content)
        ))

        onView(withId(R.id.ddate)).check(ViewAssertions.matches(
            ViewMatchers.withText(formatDateTime(news.publishedAt))
        ))

    }


}
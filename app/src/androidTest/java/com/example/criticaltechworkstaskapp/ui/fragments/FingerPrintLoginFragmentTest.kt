package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.model.Source
import com.example.criticaltechworkstaskapp.presentation.adapter.NewsAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class FingerPrintLoginFragmentTest{

    @Before
    fun setUp() {

        launchFragmentInContainer<FingerPrintLoginFragment>(fragmentArgs = null, factory = null)
    }

    @Test
    fun confirmLockIconFingerprintTextandLoginButtonViewsAreDisplayedWithCorrectText() {

        onView(ViewMatchers.withId(R.id.finger_print_text)).check(ViewAssertions.matches(
            ViewMatchers.withText(R.string.please_enter)
        ))

        onView(ViewMatchers.withId(R.id.btnAuthenticate)).check(ViewAssertions.matches(
            ViewMatchers.withText(R.string.login)
        ))

    }

}
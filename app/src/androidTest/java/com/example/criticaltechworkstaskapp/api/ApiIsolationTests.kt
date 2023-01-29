package com.example.criticaltechworkstaskapp.api

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.common.Constants
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ApiIsolationTests : TestCase() {

    @Test
    fun test_news_Api_List_Result_Success(){
        val api = TestApiImplementation.provideApi()
        val test = runBlocking {
            api.getNewsHeadlines2(Constants.MAIN_APP_NEWS_SOURCE, BuildConfig.API_KEY)
        }

        assertEquals(test.isSuccessful, true)
    }


}
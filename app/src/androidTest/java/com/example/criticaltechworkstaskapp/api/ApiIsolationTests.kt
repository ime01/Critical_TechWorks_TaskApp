package com.example.criticaltechworkstaskapp.api

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.api.TestApiImplementation
import com.example.criticaltechworkstaskapp.common.Constants.MAIN_APP_NEWS_SOURCE
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ApiIsolationTests : TestCase() {

    @Test
    fun test_NewsList_Api_Result_Success(){
        val api = TestApiImplementation.provideApi()
        val test = runBlocking {
            api.getNewsHeadlines( MAIN_APP_NEWS_SOURCE, BuildConfig.API_KEY)
        }

        assertEquals(test.isSuccessful, true)
    }

    @Test
    fun  test_pokemon_Api_List_Result_NotEmpty(){
        val api = TestApiImplementation.provideApi()
        val test = runBlocking {
            api.getNewsHeadlines( MAIN_APP_NEWS_SOURCE, BuildConfig.API_KEY)
        }

        assertEquals(test.body()?.articles?.isNotEmpty(), true)
    }


}
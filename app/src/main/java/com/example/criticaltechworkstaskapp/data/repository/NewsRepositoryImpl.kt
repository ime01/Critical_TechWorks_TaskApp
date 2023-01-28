package com.example.criticaltechworkstaskapp.data.repository


import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.common.Constants.CNN_FLAVOUR
import com.example.criticaltechworkstaskapp.common.Constants.CNN_FLAVOUR_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.common.Constants.GOOGLE_FLAVOUR
import com.example.criticaltechworkstaskapp.common.Constants.GOOGLE_FLAVOUR_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.common.Constants.MAIN_APP_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.common.Constants.REUTERS_FLAVOUR
import com.example.criticaltechworkstaskapp.common.Constants.REUTERS_FLAVOUR_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.data.remote.NewsApi
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (private val api: NewsApi): NewsRepository {

    override suspend fun getNews(): NewsResponse {
       return api.getNewsHeadlines(checkFlavourReturnNewsSource(), BuildConfig.API_KEY)
    }

    private fun checkFlavourReturnNewsSource():String{
     return   if(BuildConfig.FLAVOR.equals(REUTERS_FLAVOUR)) {
         REUTERS_FLAVOUR_NEWS_SOURCE
        } else if (BuildConfig.FLAVOR.equals(GOOGLE_FLAVOUR)) {
           GOOGLE_FLAVOUR_NEWS_SOURCE
        }else if (BuildConfig.FLAVOR.equals(CNN_FLAVOUR)){
            CNN_FLAVOUR_NEWS_SOURCE
        }else MAIN_APP_NEWS_SOURCE
    }
}
package com.example.criticaltechworkstaskapp.data.repository


import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.common.Constants.CNN_FLAVOUR_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.common.Constants.MAIN_APP_NEWS_SOURCE
import com.example.criticaltechworkstaskapp.data.remote.NewsApi
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (private val api: NewsApi): NewsRepository {

    override suspend fun getNews(): NewsResponse {
       return api.getNewsHeadlines(CNN_FLAVOUR_NEWS_SOURCE, BuildConfig.API_KEY)
    }
}
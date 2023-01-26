package com.example.criticaltechworkstaskapp.data.repository


import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.common.Constants.NEWS_SOURCE
import com.example.criticaltechworkstaskapp.data.remote.NewsApi
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsDto
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (private val api: NewsApi): NewsRepository {

    override suspend fun getNews(): NewsResponse {
       return api.getNewsHeadlines(NEWS_SOURCE, BuildConfig.API_KEY)
    }
}
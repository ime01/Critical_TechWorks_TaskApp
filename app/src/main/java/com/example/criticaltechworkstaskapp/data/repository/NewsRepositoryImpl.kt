package com.example.criticaltechworkstaskapp.data.repository


import com.example.criticaltechworkstaskapp.data.remote.NewsApi
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (private val api: NewsApi): NewsRepository {

    override suspend fun getNews(newsSource:String, apiKey:String): NewsResponse {
       return api.getNewsHeadlines(newsSource, apiKey)
    }

}
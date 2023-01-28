package com.example.criticaltechworkstaskapp.domian.repository

import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse


interface NewsRepository {

    suspend fun getNews(newsSource:String, apiKey:String): NewsResponse

}
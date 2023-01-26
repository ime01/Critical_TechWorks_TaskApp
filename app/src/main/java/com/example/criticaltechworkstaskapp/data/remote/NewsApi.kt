package com.example.criticaltechworkstaskapp.data.remote


import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsHeadlines(
        @Query("sources") newsSource: String,
        @Query("apiKey") apiKey: String) : NewsResponse
}
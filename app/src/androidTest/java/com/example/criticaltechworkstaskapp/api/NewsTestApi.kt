package com.example.criticaltechworkstaskapp.api


import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsTestApi {

    @GET("top-headlines")
    suspend fun getNewsHeadlines2(
        @Query("sources") newsSource: String,
        @Query("apiKey") apiKey: String) : Response<NewsResponse>


}
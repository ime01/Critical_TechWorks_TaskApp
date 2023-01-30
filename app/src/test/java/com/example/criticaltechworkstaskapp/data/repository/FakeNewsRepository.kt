package com.example.criticaltechworkstaskapp.data.repository

import com.example.criticaltechworkstaskapp.data.remote.dto.NewsDto
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse
import com.example.criticaltechworkstaskapp.data.remote.dto.SourceDto
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository

class FakeNewsRepository: NewsRepository {

    override suspend fun getNews(newsSource: String, apiKey: String): NewsResponse {

        return NewsResponse(
            articles = listOf(
                NewsDto(
                    author = "JOHN WICK",
                    content = "JOHN WICK SPOTTED IS BACK FIGHTING HIS WAY OUT",
                    description = "JOHN WICK SPOTTED",
                    publishedAt = "29/01/2023",
                    source = SourceDto("cnn", "CNN News"),
                    title = "JOHN WICK",
                    url = "",
                    urlToImage = ""
                ),

                NewsDto(
                    author = "JOHN WICK2",
                    content = "JOHN WICK SPOTTED IS BACK FIGHTING HIS WAY OUT2",
                    description = "JOHN WICK SPOTTED2",
                    publishedAt = "29/01/2024",
                    source = SourceDto("bbc", "BBC News"),
                    title = "JOHN WICK2",
                    url = "",
                    urlToImage = ""
                ),

                NewsDto(
                    author = "JOHN WICK3",
                    content = "JOHN WICK SPOTTED IS BACK FIGHTING HIS WAY OUT3",
                    description = "JOHN WICK SPOTTED3",
                    publishedAt = "29/01/2025",
                    source = SourceDto("sky", "SKY News"),
                    title = "JOHN WICK",
                    url = "",
                    urlToImage = ""
                )
            ),
            status = "ok",
            totalResults = 3)

    }
}
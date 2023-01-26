package com.example.criticaltechworkstaskapp.domian.repository

import com.example.criticaltechworkstaskapp.data.remote.dto.NewsDto
import com.example.criticaltechworkstaskapp.data.remote.dto.NewsResponse


interface NewsRepository {

    suspend fun getNews(): NewsResponse

}
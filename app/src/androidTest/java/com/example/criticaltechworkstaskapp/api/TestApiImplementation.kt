package com.example.criticaltechworkstaskapp.api


import com.example.criticaltechworkstaskapp.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestApiImplementation {
    fun provideApi(): TestApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TestApi::class.java)
}
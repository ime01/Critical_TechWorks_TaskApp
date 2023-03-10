package com.example.criticaltechworkstaskapp.di


import com.example.criticaltechworkstaskapp.common.Constants
import com.example.criticaltechworkstaskapp.data.remote.NewsApi
import com.example.criticaltechworkstaskapp.data.repository.NewsRepositoryImpl
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun httpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClientWithHeader = OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return okHttpClientWithHeader
    }


    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return  Retrofit.Builder()
            .client(httpClient())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCarsRepository(api: NewsApi): NewsRepository {
        return  NewsRepositoryImpl(api)
    }


}
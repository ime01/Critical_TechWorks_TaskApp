package com.example.criticaltechworkstaskapp.domian.usecase

import com.example.criticaltechworkstaskapp.common.Resource
import com.example.criticaltechworkstaskapp.data.remote.dto.toNews
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor (private val repository: NewsRepository) {


    operator fun invoke (): Flow<Resource<List<News>>> = flow {

        try {

            emit(Resource.loading())

            val news = repository.getNews().articles?.map {
                it.toNews()
            }

            emit(Resource.success(news))

        }catch (e:HttpException){
            emit(Resource.error( e.toString() ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.error("Couldn't reach the server, Check your internet connection and try again"))
        }


    }

}
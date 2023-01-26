package com.example.criticaltechworkstaskapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.criticaltechworkstaskapp.common.Resource
import com.example.criticaltechworkstaskapp.common.Status
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class NewsViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) :ViewModel() {



       val newsFromNetwork = MutableLiveData<Resource<List<News>>>()

    init {
        getNews()
    }


     fun getNews() {

         getNewsUseCase().onEach { result ->

             when (result.status) {

                 Status.SUCCESS -> {

                     newsFromNetwork.value = Resource.success(result.data!!)

                 }
                 Status.ERROR -> {

                     newsFromNetwork.value = Resource.error(result.message!!)

                 }
                 Status.LOADING -> {

                     newsFromNetwork.value = Resource.loading()

                 }

             }
         }.launchIn(viewModelScope)

     }


}
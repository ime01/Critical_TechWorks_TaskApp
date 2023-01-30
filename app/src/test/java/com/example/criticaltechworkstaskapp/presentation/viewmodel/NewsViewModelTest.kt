package com.example.criticaltechworkstaskapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.criticaltechworkstaskapp.common.Resource
import com.example.criticaltechworkstaskapp.data.repository.FakeNewsRepository
import com.example.criticaltechworkstaskapp.domian.usecase.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest{

    @get:Rule
    var rule:TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    val fakeRepoReturningData = FakeNewsRepository()
    val useCase2 = GetNewsUseCase(fakeRepoReturningData)
    private val viewModel = NewsViewModel(useCase2)

    @Test
    fun `When fetching News content state is shown`(){
        viewModel.getNews("bbc-news", "testkey")
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.newsFromNetwork.value is Resource)
    }

    @Test
    fun `When get News IS Called Data is not Empty`(){
        viewModel.getNews("bbc-news", "testkey")
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.newsFromNetwork.value?.data?.isNotEmpty() == true )
    }








}
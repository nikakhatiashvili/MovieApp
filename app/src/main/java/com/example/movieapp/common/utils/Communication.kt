package com.example.movieapp.common.utils

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface Communication<T> {

    fun map(news: T)
    suspend fun collectMovies(collector: FlowCollector<T>)

    class Base<T>(data: T) : Communication<T> {

        private val topRatedLiveData = MutableStateFlow(data)

        override fun map(news: T) {
            topRatedLiveData.value = news
        }

        override suspend fun collectMovies(collector: FlowCollector<T>) {
            topRatedLiveData.collect(collector)
        }

    }
}
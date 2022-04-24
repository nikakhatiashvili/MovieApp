package com.example.movieapp.feature_movies.domain.utils

import kotlinx.coroutines.delay

interface DelayProvider {

    suspend fun provideDelay(time: Long)
    fun getDelayTime(): Long

    class Base : DelayProvider {

        private val time: Long = 350

        override suspend fun provideDelay(time: Long) {
            delay(time)
        }

        override fun getDelayTime(): Long {
            return time
        }
    }
}
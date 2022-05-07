package com.example.movieapp.feature_movies.domain.utils

import kotlinx.coroutines.delay

interface ProvideDelay {

    suspend fun provideDelay(time: Long)
    fun getDelayTime(): Long

    class Base : ProvideDelay {

        private val time: Long = 350

        override suspend fun provideDelay(time: Long) {
            delay(time)
        }

        override fun getDelayTime(): Long {
            return time
        }
    }
}
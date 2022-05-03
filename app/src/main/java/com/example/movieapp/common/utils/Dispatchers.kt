package com.example.movieapp.common.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.latest.UpcomingMovies
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.popular.Popular
import com.example.movieapp.feature_movies.domain.model.movies_tv_shows.top_rated.TopRated
import com.example.movieapp.feature_movies.domain.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope

interface Dispatchers {
    fun launchUI(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun launchBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    fun launchDefault(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job
    suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit)

    class Base : Dispatchers {
        override fun launchUI(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(kotlinx.coroutines.Dispatchers.Main, block = block)

        override fun launchBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(kotlinx.coroutines.Dispatchers.IO, block = block)

        override fun launchDefault(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit
        ) = scope.launch(kotlinx.coroutines.Dispatchers.Default, block = block)

        override suspend fun changeToUi(block: suspend CoroutineScope.() -> Unit) {
            withContext(kotlinx.coroutines.Dispatchers.Main, block)
        }

    }
}


interface Communcation {

    fun map(news: Resource<TopRated>)
    fun observeNews(owner: LifecycleOwner, observer: Observer<Resource<TopRated>>)

    fun mapPopular(news: Resource<Popular>)
    fun observePopular(owner: LifecycleOwner, observer: Observer<Resource<Popular>>)

    fun mapMovies(news: Resource<UpcomingMovies>)
    fun observeMovies(owner: LifecycleOwner, observer: Observer<Resource<UpcomingMovies>>)

    class Base: Communcation {

        private val topRatedLiveData = MutableLiveData<Resource<TopRated>>()
        private val popularLiveData = MutableLiveData<Resource<Popular>>()
        private val upcomingMoviesLiveData = MutableLiveData<Resource<UpcomingMovies>>()

        override fun map(news: Resource<TopRated>) {
            topRatedLiveData.postValue(news)
        }

        override fun observeNews(owner: LifecycleOwner, observer: Observer<Resource<TopRated>>) {
            topRatedLiveData.observe(owner, observer)
        }

        override fun mapPopular(news: Resource<Popular>) {
            popularLiveData.postValue(news)
        }

        override fun observePopular(owner: LifecycleOwner, observer: Observer<Resource<Popular>>) {
            popularLiveData.observe(owner, observer)
        }

        override fun mapMovies(news: Resource<UpcomingMovies>) {
            upcomingMoviesLiveData.postValue(news)
        }

        override fun observeMovies(
            owner: LifecycleOwner,
            observer: Observer<Resource<UpcomingMovies>>
        ) {
            upcomingMoviesLiveData.observe(owner, observer)
        }

    }
}

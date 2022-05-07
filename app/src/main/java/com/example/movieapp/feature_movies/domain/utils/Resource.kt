package com.example.movieapp.feature_movies.domain.utils

sealed class Resource<T> (val data: T? = null, val message: String? = null, val empty: Int? = null) {
    class Loading<T>: Resource<T>()
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class EmptyData<T>(emptyState: Int? = null): Resource<T>(empty = emptyState)
}
package com.example.movieapp.feature_movies.domain.utils

import retrofit2.Response

interface ProvideResponseHandler {

    suspend fun <T> handleResponse(apicall: suspend() -> Response<T>): Resource<T>

    class Base(private val internetCheckerProvider: ProvideInternetConnectionChecker): ProvideResponseHandler {
        override suspend fun <T> handleResponse(apicall: suspend () -> Response<T>): Resource<T> {
            if (internetCheckerProvider.isNetworkConnected()) {
                try {
                    val response = apicall()
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        return Resource.Success(body)
                    }
                    return Resource.Error(response.errorBody().toString())

                } catch (e: Exception) {
                    return Resource.Error(e.printStackTrace().toString())
                }
            } else {
                return Resource.Error("No Internet Connection")
            }
        }

    }
}
package com.example.movieapp.feature_movies.data.repository

import android.provider.SyncStateContract
import com.example.movieapp.feature_movies.data.remote_data.MovieService
import com.example.movieapp.feature_movies.domain.model.top_rated.TopRatedResult
import com.example.movieapp.feature_movies.domain.repository.MoviesRepository
import com.example.movieapp.feature_movies.domain.utils.Constants
import com.example.movieapp.feature_movies.domain.utils.Resource
import org.json.JSONObject
import retrofit2.Response

class TopRatedRepositoryImpl(
    private val apiTopMovie:MovieService
):MoviesRepository {
    override suspend fun TopRatedMovies(): Resource<TopRatedResult> {
        return handleMovieResponse { apiTopMovie.getAllTopRatedMovies() }
    }

    private suspend fun <M> handleMovieResponse(
        request: suspend () -> Response<M>
    ): Resource<M> {
        return try {

                val result = request.invoke()
                val body = result.body()
                if (result.isSuccessful && body != null) {
                    Resource.Success(body)
                } else if (result.code() == 401 || result.code() == 400) {
                    val jObjError = JSONObject(result.errorBody()!!.charStream().readText())
                    Resource.Error(jObjError.getString(Constants.ERROR_JSON_NAME))
                } else {
                    Resource.Error(result.message())
                }
        } catch (e: Throwable) {
            Resource.Error(e.message.toString(), null)
        }
    }
}
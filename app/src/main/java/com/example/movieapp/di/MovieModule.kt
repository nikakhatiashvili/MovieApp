package com.example.movieapp.di


import android.content.Context
import com.example.movieapp.common.utils.Communication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import dagger.hilt.InstallIn
import javax.inject.Singleton
import com.squareup.moshi.Moshi
import dagger.hilt.components.SingletonComponent
import com.example.movieapp.common.utils.Dispatchers
import com.example.movieapp.feature_movies.data.remote_data.details.DetailsService
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.movieapp.feature_movies.domain.utils.ProvideResponseHandler
import com.example.movieapp.feature_movies.data.remote_data.movie_tv_shows.MovieService
import com.example.movieapp.feature_movies.data.remote_data.search.SearchService
import com.example.movieapp.feature_movies.data.repository.detail_repo.DetailsRepositoryImpl
import com.example.movieapp.feature_movies.domain.utils.Constants.BASE_URL
import com.example.movieapp.feature_movies.domain.repository.movie_repo.MoviesRepository
import com.example.movieapp.feature_movies.domain.use_cases.movie.movies.MoviesUseCase
import com.example.movieapp.feature_movies.data.repository.movie_repo.TopRatedRepositoryImpl
import com.example.movieapp.feature_movies.data.repository.search_repo.SearchRepositoryImpl
import com.example.movieapp.feature_movies.domain.model.search.Search
import com.example.movieapp.feature_movies.domain.repository.details_repo.DetailsRepository
import com.example.movieapp.feature_movies.domain.repository.search_repo.SearchRepository
import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailMovieCastUseCase
import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailMovieUseCaseUseCase
import com.example.movieapp.feature_movies.domain.use_cases.detail.DetailsUseCaseClass
import com.example.movieapp.feature_movies.domain.use_cases.detail.SimilarMoviesUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.popular.PopularUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.upcoming.UpcomingUseCase
import com.example.movieapp.feature_movies.domain.use_cases.movie.top_rated.TopRatedUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.MultiSearchUseCase
import com.example.movieapp.feature_movies.domain.use_cases.search.SearchUseCaseClass
import com.example.movieapp.feature_movies.domain.utils.ProvideDelay
import com.example.movieapp.feature_movies.domain.utils.ProvideInternetConnectionChecker
import com.example.movieapp.feature_movies.domain.utils.Resource
import com.example.movieapp.feature_movies.presentation.fragments.details.DetailMoviesResource
import com.example.movieapp.feature_movies.presentation.fragments.home.FullResource
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideBaseRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    @Provides
    @Singleton
    fun provideMoviesService(baseRetrofit: Retrofit): MovieService =
        baseRetrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideSearchService(baseRetrofit: Retrofit): SearchService =
        baseRetrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideDetailsService(baseRetrofit: Retrofit): DetailsService =
        baseRetrofit.create(DetailsService::class.java)


    @Provides
    fun provideSearchUseCases(repo: SearchRepository): SearchUseCaseClass {
        return SearchUseCaseClass(
            searchUseCase = MultiSearchUseCase(repo),
        )
    }

    @Provides
    fun provideUseCases(repo: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(
            topRatedUseCase = TopRatedUseCase(repo),
            popularUseCase = PopularUseCase(repo),
            upcomingUseCase = UpcomingUseCase(repo)
        )
    }

    @Provides
    fun providesDetailsUseCases(repo: DetailsRepository): DetailsUseCaseClass {
        return DetailsUseCaseClass(
            detailMovieUseCaseUseCase = DetailMovieUseCaseUseCase(repo),
            similarMoviesUseCase = SimilarMoviesUseCase(repo),
            detailMovieCast = DetailMovieCastUseCase(repo)
        )
    }


    @Provides
    @Singleton
    fun provideSearchRepository(
        searchService: SearchService,
        responseHandler: ProvideResponseHandler
    ): SearchRepository {
        return SearchRepositoryImpl(searchService, responseHandler)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(
        movieService: MovieService,
        responseHandler: ProvideResponseHandler
    ): MoviesRepository {
        return TopRatedRepositoryImpl(movieService, responseHandler)
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(
        detailsService: DetailsService,
        responseHandler: ProvideResponseHandler
    ): DetailsRepository {
        return DetailsRepositoryImpl(detailsService, responseHandler)
    }


    @Provides
    @Singleton
    fun provideResponseHandler(provideInternetConnectionChecker: ProvideInternetConnectionChecker): ProvideResponseHandler =
        ProvideResponseHandler.Base(provideInternetConnectionChecker)

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()

    @Provides
    fun provideDelay(): ProvideDelay = ProvideDelay.Base()

    @Provides
    fun provideCommunication(): Communication<FullResource> = Communication.Base(FullResource.Empty)

    @Provides
    fun provideSearchCommunication(): Communication<Resource<Search>> =
        Communication.Base(Resource.Loading())

    @Provides
    fun provideDetailsMoviesResource(): Communication<DetailMoviesResource> =
        Communication.Base(DetailMoviesResource.Empty)

    @Provides
    fun provideInternetCheckConnection(@ApplicationContext context: Context): ProvideInternetConnectionChecker =
        ProvideInternetConnectionChecker.NetworkHelper(context)

}
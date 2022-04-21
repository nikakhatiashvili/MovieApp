package com.example.movieapp.app

import com.example.movieapp.feature_movies.domain.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiService {

//    @Provides
//    @Singleton
//    fun provideRetrofitCurrency(): NewsApi =
//        Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(
//                MoshiConverterFactory.create(
//                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//                )
//            )
//            .build()
//            .create(NewsApi::class.java)
//    val Service by lazy { createService() }
//
//    private val loggingInterceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    @Singleton
//    @Provides
//    fun createService(): CryptoNetwork {
//        val retrofitBuilder = Retrofit.Builder()
//        retrofitBuilder.client(
//            OkHttpClient().newBuilder()
//                .addInterceptor(loggingInterceptor)
//                .build()
//        )
//        retrofitBuilder.addConverterFactory(mochiConvertor())
//        return retrofitBuilder.build().create(CryptoNetwork::class.java)
//    }
//
//
//    private fun mochiConvertor()=
//        MoshiConverterFactory.create(
//            Moshi.Builder()
//                .addLast(KotlinJsonAdapterFactory())
//                .build()
//        )
}
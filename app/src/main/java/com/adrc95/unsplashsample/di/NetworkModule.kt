package com.adrc95.unsplashsample.di

import com.adrc95.unsplashsample.BuildConfig
import com.adrc95.unsplashsample.data.service.APIService
import com.adrc95.unsplashsample.common.HeadersInterceptor
import com.adrc95.unsplashsample.data.service.UnsplashApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Named("unsplash")
    fun providesEndpoint(): String {
        return "https://api.unsplash.com"
    }

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) httpLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY else httpLoggingInterceptor.level =
            HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    fun providesHeaderInterceptor (): HeadersInterceptor = HeadersInterceptor()

    @ExperimentalSerializationApi
    @Provides
    @Named("json_factory")
    fun provideJsonFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideUnsplashApiService(
        @Named("unsplash") endpoint: String,
        headerInterceptor: HeadersInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("json_factory") jsonFactory : Converter.Factory,
    ): APIService<UnsplashApiService> {
        return APIService(
            UnsplashApiService::class.java,
            endpoint,
            jsonFactory,
            arrayOf(headerInterceptor, httpLoggingInterceptor)
        )
    }

}
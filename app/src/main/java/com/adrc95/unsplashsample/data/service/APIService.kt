package com.adrc95.unsplashsample.data.service

import com.adrc95.unsplashsample.common.EitherCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class APIService<T> constructor(
    serviceClass: Class<T>,
    private val baseURL: String,
    private val converterFactory: Converter.Factory,
    private val interceptors: Array<Interceptor>
) {
    val service: T

    init {
        service = initApiService().create(serviceClass)
    }

    private fun initApiService(): Retrofit {
        val client = OkHttpClient.Builder()

        interceptors.map { client.addInterceptor(it) }

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(EitherCallAdapterFactory())
            .client(client.build())
            .build()
    }

}
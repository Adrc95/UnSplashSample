package com.adrc95.unsplashsample.common

import com.adrc95.unsplashsample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept-Version", "v1")
            .addHeader("Authorization", "Client-ID ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}
package com.adrc95.unsplashsample.di

import android.app.Application
import android.content.Context
import com.adrc95.usecase.Invoker
import com.adrc95.usecase.UseCaseInvoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesInvoker(): Invoker = UseCaseInvoker()

}
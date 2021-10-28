package com.adrc95.unsplashsample.di

import android.app.Application
import android.content.Context
import com.adrc95.unsplashsample.ui.common.navigator.Navigator
import com.adrc95.unsplashsample.ui.common.navigator.UnsplashAppNavigator
import com.adrc95.usecase.Invoker
import com.adrc95.usecase.UseCaseInvoker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.Binds




@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesInvoker(): Invoker = UseCaseInvoker()

    @Provides
    @Singleton
    fun providesNavigator(context: Context): Navigator =
        UnsplashAppNavigator(context)

}
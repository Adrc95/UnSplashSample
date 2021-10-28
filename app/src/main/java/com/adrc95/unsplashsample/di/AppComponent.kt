package com.adrc95.unsplashsample.di

import android.app.Application
import android.content.Context
import com.adrc95.unsplashsample.UnsplashApplication
import com.adrc95.unsplashsample.ui.common.navigator.UnsplashAppNavigator
import com.adrc95.usecase.Invoker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        InjectorsModule::class
    ]
)

interface AppComponent : AndroidInjector<UnsplashApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


}

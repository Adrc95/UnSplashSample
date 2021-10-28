package com.adrc95.unsplashsample.di

import com.adrc95.unsplashsample.ui.detail.DetailActivity
import com.adrc95.unsplashsample.ui.detail.DetailModule
import com.adrc95.unsplashsample.ui.main.MainActivity
import com.adrc95.unsplashsample.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class InjectorsModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    @ActivityScope
    abstract fun providesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    @ActivityScope
    abstract fun providesDetailActivity(): DetailActivity
}
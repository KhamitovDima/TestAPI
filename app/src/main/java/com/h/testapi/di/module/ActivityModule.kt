package com.h.testapi.di.module

import com.h.testapi.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivityAndroidInjector(): MainActivity
}
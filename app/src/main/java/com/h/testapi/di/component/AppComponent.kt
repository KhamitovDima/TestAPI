package com.h.testapi.di.component

import android.app.Application
import com.h.testapi.di.App
import com.h.testapi.di.module.ActivityModule
import com.h.testapi.di.module.FragmentModule
import com.h.testapi.di.module.NetworkModule
import com.h.testapi.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class,
        ViewModelModule::class
    )
)
interface AppComponent: AndroidInjector<App> {

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
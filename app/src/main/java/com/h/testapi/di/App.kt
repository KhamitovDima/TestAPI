package com.h.testapi.di

import android.app.Application
import com.h.testapi.di.component.DaggerAppComponent
import com.h.testapi.util.PrefsHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        PrefsHelper.init(this)

        if (PrefsHelper.readToken() == "")
            PrefsHelper.writeToken("gFSv14S-mN-LUN8dCM")

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
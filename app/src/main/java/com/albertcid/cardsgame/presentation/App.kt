package com.albertcid.cardsgame.presentation

import com.albertcid.cardsgame.di.ApplicationComponent
import com.albertcid.cardsgame.di.ApplicationComponentFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {
    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = ApplicationComponentFactory.create(this)
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }
}
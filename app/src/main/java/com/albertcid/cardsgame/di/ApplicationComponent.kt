package com.albertcid.cardsgame.di


import com.albertcid.cardsgame.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AppModule::class,
        ActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: App
        ): ApplicationComponent
    }
}

object ApplicationComponentFactory : ApplicationComponent.Factory by DaggerApplicationComponent.factory()
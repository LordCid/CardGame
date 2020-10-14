package com.albertcid.cardsgame.di

import com.albertcid.cardsgame.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
//    includes = [
//        UseCaseModule::class,
//        GameModule::class
//    ]
)
interface ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainListActivity(): MainActivity

}
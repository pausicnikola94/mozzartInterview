package com.mozzartinterview

import android.app.Application
import com.mozzartinterview.core.di.networkModule
import com.mozzartinterview.feature_kino_game.di.kinoGameModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MozzartApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MozzartApplication)
            modules(networkModule, kinoGameModule)
        }
    }
}
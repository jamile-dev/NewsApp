package dev.jamile.newsapp

import android.app.Application
import dev.jamile.newsapp.di.dataModule
import dev.jamile.newsapp.di.domainModule
import dev.jamile.newsapp.di.networkModule
import dev.jamile.newsapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp(): Application() {

    override fun onCreate() {
        super.onCreate()
        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin {
            androidContext(this@NewsApp)
            modules(listOf(dataModule, domainModule, networkModule, uiModule))
        }
    }
}
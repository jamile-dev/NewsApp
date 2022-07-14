package dev.jamile.newsapp.di

import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.network.NewsApi
import org.koin.dsl.module

val dataModule = module {

    single {
        NewsRepository(get<NewsApi>())
    }

}
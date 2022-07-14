package dev.jamile.newsapp.di

import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.domain.NewsUseCase
import dev.jamile.newsapp.domain.mapper.NewsMapper
import org.koin.dsl.module

val domainModule = module {

    single {
        NewsUseCase(get<NewsRepository>(), get<NewsMapper>())
    }

    single {
        NewsMapper()
    }
}
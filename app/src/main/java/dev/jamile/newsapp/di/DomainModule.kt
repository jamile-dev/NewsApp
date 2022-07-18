package dev.jamile.newsapp.di

import dev.jamile.newsapp.domain.NewsUseCase
import dev.jamile.newsapp.domain.mapper.NewsMapper
import org.koin.dsl.module

val domainModule = module {

    single {
        NewsUseCase(
            repository = get(),
            mapper = get()
        )
    }

    single {
        NewsMapper()
    }
}
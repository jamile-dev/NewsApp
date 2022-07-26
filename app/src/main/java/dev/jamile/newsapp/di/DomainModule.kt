package dev.jamile.newsapp.di

import dev.jamile.newsapp.domain.GetListTopNewsUseCase
import dev.jamile.newsapp.domain.mapper.NewsMapper
import org.koin.dsl.module

val domainModule = module {

    single {
        GetListTopNewsUseCase(
            repository = get(),
            mapper = get()
        )
    }

    single {
        NewsMapper()
    }
}
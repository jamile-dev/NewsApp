package dev.jamile.newsapp.di

import dev.jamile.newsapp.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        NewsViewModel(
            newsUseCase = get(),
            providerContext = get()
        )
    }
}
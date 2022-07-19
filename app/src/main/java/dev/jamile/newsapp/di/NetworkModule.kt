package dev.jamile.newsapp.di

import dev.jamile.newsapp.BuildConfig.API_URL
import dev.jamile.newsapp.common.ProviderContext
import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.network.NewsApi
import dev.jamile.newsapp.network.interceptor.RequestInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Retrofit> {
        buildRetrofit()
    }

    single {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single {
        ProviderContext()
    }
}

private fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_URL)
        .client(RequestInterceptor.setupOkHttp().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
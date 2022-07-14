package dev.jamile.newsapp.di

import dev.jamile.newsapp.BuildConfig.API_URL
import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.network.NewsApi
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

}

private fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
package dev.jamile.newsapp.data

import dev.jamile.newsapp.common.safeApiCall
import dev.jamile.newsapp.network.NewsApi

class NewsRepository(
    private val api: NewsApi
) {

    suspend fun list(country: String) = safeApiCall {
        api.list(country)
    }

}
package dev.jamile.newsapp.network

import dev.jamile.newsapp.BuildConfig.API_KEY
import dev.jamile.newsapp.network.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun list(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}
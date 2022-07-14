package dev.jamile.newsapp.domain

import dev.jamile.newsapp.common.ResultType
import dev.jamile.newsapp.common.handleResultType
import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.domain.mapper.NewsMapper
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.network.ResponseError
import dev.jamile.newsapp.network.model.NewsResponse

class NewsUseCase(
    private val repository: NewsRepository,
    private val mapper: NewsMapper
) {

    suspend fun list(
        country: String,
        onSuccess: (List<News>) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result: ResultType<NewsResponse>?
        result = repository.list(country)

        result.handleResultType(
            success = {
                val newsList = mutableListOf<News>()
                it.articles.forEach{ article ->
                    newsList.add(mapper.convert(article))
                }
                onSuccess(newsList)
            },
            error = {
                onError(it)
            }
        )

    }

}
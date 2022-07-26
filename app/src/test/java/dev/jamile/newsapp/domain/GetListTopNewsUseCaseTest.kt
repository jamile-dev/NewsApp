package dev.jamile.newsapp.domain

import dev.jamile.newsapp.common.ResultType
import dev.jamile.newsapp.data.NewsRepository
import dev.jamile.newsapp.domain.core.ParametersDTO
import dev.jamile.newsapp.domain.mapper.NewsMapper
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.network.ResponseError
import dev.jamile.newsapp.network.model.NewsResponse
import dev.jamile.newsapp.util.readJsonFile
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class GetListTopNewsUseCaseTest {
    private val newsResult = News(
        "Globo",
        "title",
        "description",
        "url",
        "urlToImage",
        "sourceName"
    )
    private val newsResponseResult = readJsonFile<NewsResponse>("news_topheadlines_200.json")
    private val newsRepository: NewsRepository = mockk()
    private val newsMapper: NewsMapper = mockk()
    private val useCase by lazy {
        GetListTopNewsUseCase(newsRepository, newsMapper)
    }

    @Test
    fun `should retrieve news of country BR`() = runBlocking {
        var result: List<News>? = null
        val expectedResult = ResultType.Success(newsResponseResult)
        coEvery { newsRepository.list(country = "br") } coAnswers { expectedResult }
        coEvery { newsMapper.convert(any()) } returns newsResult

        useCase.execute(
            parameters = ParametersDTO{ add("country", "br") },
            onSuccess = {
                result = it
            },
            onError = {}
        )

        assertEquals(20, result?.size)
    }

    @Test
    fun `should return error when request failed`() = runBlocking {
        val expectedResult = ResultType.Fail(ResponseError(Throwable("Error")))
        var result: ResponseError? = null
        coEvery { newsRepository.list(country = "ar") } coAnswers { expectedResult }

        useCase.execute(
            parameters = ParametersDTO { add("country", "ar") },
            onSuccess = {},
            onError = {
                result = it
            })

        assertEquals(result!!.cause, expectedResult.exception.cause)
        Assert.assertEquals("Error", result?.cause?.message)
    }

}
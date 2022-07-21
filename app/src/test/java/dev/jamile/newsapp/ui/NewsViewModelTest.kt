package dev.jamile.newsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.jamile.newsapp.domain.NewsUseCase
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.network.ResponseError
import dev.jamile.newsapp.util.TestProviderContext
import dev.jamile.newsapp.util.readJsonFile
import io.mockk.coEvery
import io.mockk.invoke
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val newsUseCase: NewsUseCase = mockk()
    private val newsList = readJsonFile<List<News>>("news_list.json")
    private val viewModel by lazy {
        NewsViewModel(newsUseCase, TestProviderContext())
    }

    @Test
    fun `should retrieve news`() {
        coEvery {
            newsUseCase.list(
                country = "br",
                onSuccess = captureLambda(),
                onError = any()
            )
        } coAnswers {
            lambda<((List<News>) -> Unit)>().invoke(newsList)
        }

        viewModel.fetchNews()
        assertEquals(5, viewModel.newList.value?.data?.size)
    }

    @Test
    fun `should show message error when list news return callback onError`() {
        val responseErrorResult = ResponseError(cause = Throwable("Error"))
        coEvery {
            newsUseCase.list(
                country = "br",
                onSuccess = any(),
                onError = captureLambda()
            )
        } coAnswers {
            lambda<((ResponseError) -> Unit)>().invoke(responseErrorResult)
        }

        viewModel.fetchNews()
        assertEquals("Error", viewModel.newList.value?.error?.cause?.message)
    }

}
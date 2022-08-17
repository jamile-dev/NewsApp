package dev.jamile.newsapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.jamile.newsapp.domain.GetListTopNewsUseCase
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.network.ResponseError
import dev.jamile.newsapp.ui.NewsViewModel
import dev.jamile.newsapp.util.TestProviderContext
import dev.jamile.newsapp.util.readJsonFile
import io.mockk.coEvery
import io.mockk.invoke
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val getListTopNewsUseCase: GetListTopNewsUseCase = mockk()
    private val newsList = readJsonFile<List<News>>("news_list.json")
    private val viewModel by lazy {
        NewsViewModel(getListTopNewsUseCase, TestProviderContext())
    }

    @Test
    fun `should retrieve news`() {
        coEvery {
            getListTopNewsUseCase.execute(
                parameters = any(),
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
            getListTopNewsUseCase.execute(
                parameters = any(),
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
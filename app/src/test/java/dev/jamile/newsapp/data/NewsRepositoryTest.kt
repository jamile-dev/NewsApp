package dev.jamile.newsapp.data

import dev.jamile.newsapp.network.NewsApi
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NewsRepositoryTest {
    private val newsApi: NewsApi = mockk()
    private val repository by lazy {
        NewsRepository(newsApi)
    }

    @Test
    fun `should verify NewsApi was called`() = runBlocking {
        repository.list(country = "BR")

        coVerify {
            newsApi.list(country = "BR")
        }
    }

}
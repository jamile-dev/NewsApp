package dev.jamile.newsapp.domain.mapper

import dev.jamile.newsapp.network.model.Article
import dev.jamile.newsapp.network.model.Source
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class NewsMapperTest {
    private val mapper by lazy {
        NewsMapper()
    }

    @Test
    fun `should convert article to news`() {
        val article = Article(
            author = "author",
            content = "content",
            description = "description",
            publishedAt = "18/07/2022",
            title = "title",
            url = "url",
            urlToImage = "urlToImage",
            source = Source(id = "id", name = "sourceName")
        )

        val result = mapper.convert(article)

        assertNotNull(result)
        assertEquals("author", result.author)
        assertEquals("description", result.description)
        assertEquals("title", result.title)
        assertEquals("url", result.url)
        assertEquals("urlToImage", result.urlToImage)
        assertEquals("sourceName", result.sourceName)
    }

}
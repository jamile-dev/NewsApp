package dev.jamile.newsapp.domain.mapper

import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.network.model.Article

class NewsMapper: GeneralMapper<Article, News> {

    override fun convert(model: Article) =
        News(
            author = model.author ?: "",
            title = model.title ?: "",
            description = model.description ?: "",
            url = model.url ?: "",
            urlToImage = model.urlToImage ?: "",
            sourceName = model.source?.name ?: ""
        )

}
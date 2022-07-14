package dev.jamile.newsapp.domain.model

class News(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val sourceName: String
) {
}
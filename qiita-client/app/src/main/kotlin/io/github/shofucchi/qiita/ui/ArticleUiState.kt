package io.github.shofucchi.qiita.ui

data class ArticleUiState(
    val articles: List<Article> = emptyList()
)

data class Article(
    val title: String,
    val url: String
)
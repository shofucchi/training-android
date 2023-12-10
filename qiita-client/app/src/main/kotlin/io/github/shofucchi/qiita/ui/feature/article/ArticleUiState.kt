package io.github.shofucchi.qiita.ui.feature.article

data class ArticleUiState(
    val articles: List<Article> = emptyList()
)

data class Article(
    val title: String,
    val url: String
)
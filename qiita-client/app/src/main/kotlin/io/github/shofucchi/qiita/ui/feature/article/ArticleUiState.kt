package io.github.shofucchi.qiita.ui.feature.article

data class ArticleUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false
)

data class Article(
    val title: String,
    val url: String,
    val profileImageUrl: String,
    val updatedAt: String?
)
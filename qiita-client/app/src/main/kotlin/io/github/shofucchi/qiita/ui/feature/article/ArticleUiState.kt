package io.github.shofucchi.qiita.ui.feature.article

data class ArticleUiState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val errorReason: ErrorReason? = null
)

data class Article(
    val title: String,
    val url: String,
    val profileImageUrl: String,
    val updatedAt: String?
)

enum class ErrorReason {
    NETWORK_ERROR,
    HTTP_ERROR,
    UNKNOWN_ERROR,
}
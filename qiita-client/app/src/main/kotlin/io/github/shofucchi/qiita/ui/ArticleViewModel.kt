package io.github.shofucchi.qiita.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.shofucchi.qiita.data.QiitaLocalDataSource
import io.github.shofucchi.qiita.data.QiitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ArticleViewModel {
    val uiState: StateFlow<ArticleUiState>
    fun fetchArticles()
}

class ArticleViewModelImpl(private val qiitaRepository: QiitaRepository) : ViewModel(),
    ArticleViewModel {
    private val _uiState = MutableStateFlow(ArticleUiState())
    override val uiState = _uiState.asStateFlow()

    companion object {
        val Factory: ViewModelProvider.Factory
            get() = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ArticleViewModelImpl(QiitaRepository(QiitaLocalDataSource())) as T
                }
            }
    }

    override fun fetchArticles() {
        val articles = qiitaRepository.fetchArticles()
        _uiState.update {
            it.copy(articles = articles.map { article -> Article(article.title, article.url) })
        }
    }
}

class ArticleViewModelFake : ViewModel(), ArticleViewModel {
    private val _uiState = MutableStateFlow(ArticleUiState())
    override val uiState = _uiState.asStateFlow()

    override fun fetchArticles() {
        _uiState.update {
            it.copy(articles = (0..100).map { index ->
                Article("Article $index", "https://example.com")
            })
        }
    }

}
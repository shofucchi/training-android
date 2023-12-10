package io.github.shofucchi.qiita.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.shofucchi.qiita.data.repository.QiitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ArticleViewModel {
    val uiState: StateFlow<ArticleUiState>
    fun fetchArticles()
}

class ArticleViewModelImpl(private val qiitaRepository: QiitaRepository) : ViewModel(),
    ArticleViewModel {
    private val _uiState = MutableStateFlow(ArticleUiState())
    override val uiState = _uiState.asStateFlow()

    class Factory(private val qiitaRepository: QiitaRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ArticleViewModelImpl(qiitaRepository) as T
    }

    override fun fetchArticles() {
        viewModelScope.launch {
            val articles = qiitaRepository.fetchArticles()
            _uiState.update {
                it.copy(articles = articles.map { article -> Article(article.title, article.url) })
            }
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
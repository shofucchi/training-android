package io.github.shofucchi.qiita.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.shofucchi.qiita.data.repository.QiitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(private val qiitaRepository: QiitaRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleUiState())
    val uiState = _uiState.asStateFlow()

    class Factory(private val qiitaRepository: QiitaRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ArticleViewModel(qiitaRepository) as T
    }

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            val articles = qiitaRepository.getArticles()
            _uiState.update {
                it.copy(articles = articles.map { article -> Article(article.title, article.url) })
            }
        }
    }

}
package io.github.shofucchi.qiita.ui.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.shofucchi.qiita.data.model.ApiResult
import io.github.shofucchi.qiita.data.model.AppError
import io.github.shofucchi.qiita.data.repository.QiitaRepository
import io.github.shofucchi.qiita.utility.toDate
import io.github.shofucchi.qiita.utility.toFormattedString
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
            _uiState.update { it.copy(isLoading = true) }
            val result = qiitaRepository.getArticles()
            val articles = qiitaRepository.getArticlesFromCache()
            _uiState.update {
                it.copy(
                    articles = articles.map { article ->
                        Article(
                            article.title,
                            article.url,
                            article.profileImageUrl,
                            article.updatedAt.toDate()?.toFormattedString()
                        )
                    },
                    isLoading = false,
                    errorReason = if (result is ApiResult.Failure) {
                        when (result.error) {
                            AppError.NetworkError -> ErrorReason.NETWORK_ERROR
                            AppError.HttpError -> ErrorReason.HTTP_ERROR
                            AppError.UnknownError -> ErrorReason.UNKNOWN_ERROR
                        }
                    } else null
                )
            }
        }
    }

}
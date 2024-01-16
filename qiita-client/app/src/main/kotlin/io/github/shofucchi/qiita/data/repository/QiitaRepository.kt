package io.github.shofucchi.qiita.data.repository

import io.github.shofucchi.qiita.data.dao.ArticleDao
import io.github.shofucchi.qiita.data.datasource.QiitaDataSource
import io.github.shofucchi.qiita.data.model.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class QiitaRepository(
    private val qiitaDataSource: QiitaDataSource,
    private val articleDao: ArticleDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getArticles() = withContext(ioDispatcher) {
        val result = qiitaDataSource.getArticles()
        if (result is ApiResult.Success) {
            articleDao.upsertAll(result.data)
        }
        return@withContext result
    }

    suspend fun getArticlesFromCache() = withContext(ioDispatcher) {
        return@withContext articleDao.getAll().first()
    }
}
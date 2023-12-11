package io.github.shofucchi.qiita.data.repository

import io.github.shofucchi.qiita.data.dao.ArticleDao
import io.github.shofucchi.qiita.data.datasource.QiitaDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class QiitaRepository(
    private val qiitaDataSource: QiitaDataSource,
    private val articleDao: ArticleDao,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getArticles() = withContext(ioDispatcher) {
        try {
            val articles = qiitaDataSource.getArticles()
            articleDao.upsertAll(articles)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext articleDao.getAll().first()
    }
}
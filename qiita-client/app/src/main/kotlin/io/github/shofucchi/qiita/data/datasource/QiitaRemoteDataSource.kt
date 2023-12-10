package io.github.shofucchi.qiita.data.datasource

import io.github.shofucchi.qiita.data.dao.ArticleDao
import io.github.shofucchi.qiita.data.api.QiitaApi
import io.github.shofucchi.qiita.data.model.toArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class QiitaRemoteDataSource(
    private val articleDao: ArticleDao,
    private val qiitaApi: QiitaApi,
    private val ioDispatcher: CoroutineDispatcher
) : QiitaDataSource {

    override suspend fun fetchArticles() = withContext(ioDispatcher) {
        try {
            qiitaApi.fetchArticles()
                .map { it.toArticle() }
                .let { articleDao.upsertAll(it) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext articleDao.getAll().first()
    }
}
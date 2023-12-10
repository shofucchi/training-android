package io.github.shofucchi.qiita.data

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
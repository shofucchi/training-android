package io.github.shofucchi.qiita.data.datasource

import io.github.shofucchi.qiita.data.model.Article
import io.github.shofucchi.qiita.data.dao.ArticleDao
import io.github.shofucchi.qiita.data.api.QiitaApi
import kotlinx.coroutines.CoroutineDispatcher

interface QiitaDataSource {
    suspend fun fetchArticles(): List<Article>
}

fun QiitaDataSource(
    articleDao: ArticleDao,
    qiitaApi: QiitaApi,
    ioDispatcher: CoroutineDispatcher
): QiitaDataSource = QiitaRemoteDataSource(
    articleDao = articleDao,
    qiitaApi = qiitaApi,
    ioDispatcher = ioDispatcher
)
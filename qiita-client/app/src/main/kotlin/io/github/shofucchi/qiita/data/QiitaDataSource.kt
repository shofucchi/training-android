package io.github.shofucchi.qiita.data

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
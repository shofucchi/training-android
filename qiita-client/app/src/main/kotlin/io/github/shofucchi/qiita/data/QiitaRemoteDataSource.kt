package io.github.shofucchi.qiita.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class QiitaRemoteDataSource(
    private val qiitaApi: QiitaApi,
    private val ioDispatcher: CoroutineDispatcher
) : QiitaDataSource {

    override suspend fun fetchArticles() = withContext(ioDispatcher) { qiitaApi.fetchArticles() }
}
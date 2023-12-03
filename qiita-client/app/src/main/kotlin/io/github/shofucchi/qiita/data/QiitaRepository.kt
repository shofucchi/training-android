package io.github.shofucchi.qiita.data

class QiitaRepository(private val qiitaDataSource: QiitaDataSource) {
    suspend fun fetchArticles() = this.qiitaDataSource.fetchArticles()
}
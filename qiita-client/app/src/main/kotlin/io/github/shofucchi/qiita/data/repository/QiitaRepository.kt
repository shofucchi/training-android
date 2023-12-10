package io.github.shofucchi.qiita.data.repository

import io.github.shofucchi.qiita.data.datasource.QiitaDataSource

class QiitaRepository(private val qiitaDataSource: QiitaDataSource) {
    suspend fun fetchArticles() = this.qiitaDataSource.fetchArticles()
}
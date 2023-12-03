package io.github.shofucchi.qiita.data

interface QiitaDataSource {
    suspend fun fetchArticles(): List<QiitaApiModel>
}
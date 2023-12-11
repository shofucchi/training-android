package io.github.shofucchi.qiita.data.datasource

import io.github.shofucchi.qiita.data.model.Article

interface QiitaDataSource {
    suspend fun getArticles(): List<Article>
}
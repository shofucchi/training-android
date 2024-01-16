package io.github.shofucchi.qiita.data.datasource

import io.github.shofucchi.qiita.data.model.ApiResult
import io.github.shofucchi.qiita.data.model.Article

interface QiitaDataSource {
    suspend fun getArticles(): ApiResult<List<Article>>
}
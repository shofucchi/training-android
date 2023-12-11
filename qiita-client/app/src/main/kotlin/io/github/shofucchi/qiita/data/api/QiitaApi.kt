package io.github.shofucchi.qiita.data.api

import io.github.shofucchi.qiita.data.model.QiitaApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApi {
    @GET("api/v2/items")
    suspend fun getArticles(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): List<QiitaApiModel>
}
package io.github.shofucchi.qiita.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.shofucchi.qiita.data.model.QiitaApiModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApi {
    @GET("api/v2/items")
    suspend fun fetchArticles(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): List<QiitaApiModel>
}

val json = Json { ignoreUnknownKeys = true }

fun buildQiitaApi(): QiitaApi = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl("https://qiita.com")
    .build()
    .create(QiitaApi::class.java)
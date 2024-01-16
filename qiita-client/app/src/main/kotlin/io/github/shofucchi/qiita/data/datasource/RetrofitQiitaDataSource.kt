package io.github.shofucchi.qiita.data.datasource

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.shofucchi.qiita.data.api.QiitaApi
import io.github.shofucchi.qiita.data.model.ApiResult
import io.github.shofucchi.qiita.data.model.toAppError
import io.github.shofucchi.qiita.data.model.toArticle
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class RetrofitQiitaDataSource(json: Json) : QiitaDataSource {

    private val qiitaApi: QiitaApi = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://qiita.com")
        .build()
        .create(QiitaApi::class.java)

    override suspend fun getArticles() = try {
        val articles = this.qiitaApi.getArticles().map { it.toArticle() }
        ApiResult.Success(articles)
    } catch (e: Exception) {
        ApiResult.Failure(e.toAppError())
    }
}
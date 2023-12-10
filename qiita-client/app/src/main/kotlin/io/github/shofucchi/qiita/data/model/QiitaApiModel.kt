package io.github.shofucchi.qiita.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QiitaApiModel(
    val title: String,
    val url: String
)

fun QiitaApiModel.toArticle(): Article = Article(
    id = url,
    title = title,
    url = url,
    createdAt = "",
    updatedAt = ""
)
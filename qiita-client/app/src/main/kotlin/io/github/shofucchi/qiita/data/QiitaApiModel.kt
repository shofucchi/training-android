package io.github.shofucchi.qiita.data

import kotlinx.serialization.Serializable

@Serializable
data class QiitaApiModel(
    val title: String,
    val url: String
)
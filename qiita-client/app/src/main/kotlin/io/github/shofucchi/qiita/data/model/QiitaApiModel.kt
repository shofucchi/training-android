package io.github.shofucchi.qiita.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaApiModel(
    val id: String,
    val title: String,
    val url: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val user: User
)

@Serializable
data class User(
    @SerialName("profile_image_url") val profileImageUrl: String
)

fun QiitaApiModel.toArticle(): Article = Article(
    id = id,
    title = title,
    url = url,
    profileImageUrl = user.profileImageUrl,
    createdAt = createdAt,
    updatedAt = updatedAt
)
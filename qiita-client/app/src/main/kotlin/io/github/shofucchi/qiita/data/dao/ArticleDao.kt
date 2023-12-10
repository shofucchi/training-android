package io.github.shofucchi.qiita.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.shofucchi.qiita.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<Article>>

    @Query("SELECT * FROM article WHERE id = :id")
    fun findById(id: String): Flow<Article?>

    @Upsert
    suspend fun upsertAll(articles: List<Article>)
}
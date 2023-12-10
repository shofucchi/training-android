package io.github.shofucchi.qiita.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
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
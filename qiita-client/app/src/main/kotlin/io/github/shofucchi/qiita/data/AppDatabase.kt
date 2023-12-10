package io.github.shofucchi.qiita.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        private const val DATABASE_NAME = "qiita.db"

        @Volatile private var instance: AppDatabase? = null

        fun instance(context: Context): AppDatabase {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
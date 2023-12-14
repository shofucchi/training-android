package io.github.shofucchi.qiita.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.shofucchi.qiita.data.dao.ArticleDao
import io.github.shofucchi.qiita.data.database.AppDatabase
import io.github.shofucchi.qiita.utility.articleA
import io.github.shofucchi.qiita.utility.articleB
import io.github.shofucchi.qiita.utility.articleC
import io.github.shofucchi.qiita.utility.articleD
import io.github.shofucchi.qiita.utility.articleE
import io.github.shofucchi.qiita.utility.articleF
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var articleDao: ArticleDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        articleDao = database.articleDao()
        articleDao.upsertAll(listOf(articleA, articleB, articleC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAll() = runBlocking {
        val articleList = articleDao.getAll().first()
        assertThat(articleList.size).isEqualTo(3)
        assertThat(articleList[0]).isEqualTo(articleA)
        assertThat(articleList[1]).isEqualTo(articleB)
        assertThat(articleList[2]).isEqualTo(articleC)
    }

    @Test
    fun testFindById() = runBlocking {
        val article = articleDao.findById("1").first()
        assertThat(article).isEqualTo(articleA)
    }

    @Test
    fun testUpsertAll() = runBlocking {
        articleDao.upsertAll(listOf(articleD, articleE, articleF))
        val articleList = articleDao.getAll().first()
        assertThat(articleList.size).isEqualTo(6)
        assertThat(articleList).containsExactly(
            articleA,
            articleB,
            articleC,
            articleD,
            articleE,
            articleF
        )
        assertThat(articleList[3]).isEqualTo(articleD)
        assertThat(articleList[4]).isEqualTo(articleE)
        assertThat(articleList[5]).isEqualTo(articleF)
    }
}
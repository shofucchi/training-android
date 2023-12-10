package io.github.shofucchi.qiita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.shofucchi.qiita.data.database.AppDatabase
import io.github.shofucchi.qiita.data.repository.QiitaRepository
import io.github.shofucchi.qiita.data.api.buildQiitaApi
import io.github.shofucchi.qiita.data.datasource.QiitaDataSource
import io.github.shofucchi.qiita.ui.feature.article.ArticleScreen
import io.github.shofucchi.qiita.ui.feature.article.ArticleViewModelImpl
import io.github.shofucchi.qiita.ui.theme.QiitaTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QiitaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val articleViewModel: ArticleViewModelImpl by viewModels {
                        val database = AppDatabase.instance(applicationContext)
                        val qiitaDataSource = QiitaDataSource(
                            database.articleDao(),
                            buildQiitaApi(),
                            Dispatchers.IO
                        )
                        val qiitaRepository = QiitaRepository(qiitaDataSource)
                        ArticleViewModelImpl.Factory(qiitaRepository)
                    }
                    ArticleScreen(articleViewModel = articleViewModel)
                }
            }
        }
    }
}
package io.github.shofucchi.qiita.ui.feature.article

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.shofucchi.qiita.R
import io.github.shofucchi.qiita.ui.feature.article.component.ArticleListItem
import io.github.shofucchi.qiita.ui.feature.common.LoadingScreen
import io.github.shofucchi.qiita.ui.feature.common.component.AppBar
import io.github.shofucchi.qiita.ui.feature.common.component.ErrorToast
import io.github.shofucchi.qiita.ui.feature.common.consts.AppBarTitleConst
import io.github.shofucchi.qiita.utility.toDate
import io.github.shofucchi.qiita.utility.toFormattedString

@Composable
fun ArticleScreen(articleUiState: ArticleUiState) {
    Scaffold(topBar = { AppBar(appBarTitleConst = AppBarTitleConst.Article) }) {
        if (articleUiState.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        } else {
            if (articleUiState.errorReason != null) {
                ErrorToast(
                    context = LocalContext.current,
                    errorReason = articleUiState.errorReason
                )
            }
            ArticleList(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(it),
                articles = articleUiState.articles,
                context = LocalContext.current
            )
        }
    }
}


@Composable
fun ArticleList(modifier: Modifier, articles: List<Article>, context: Context) {
    Column(modifier = modifier) {
        for (article in articles) {
            ArticleListItem(
                title = article.title,
                profileImageUrl = article.profileImageUrl,
                updatedAt = article.updatedAt ?: stringResource(id = R.string.invalid_updated_at)
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                context.startActivity(intent)
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    val articleUiState = ArticleUiState().copy(
        articles = (0..100).map { index ->
            Article(
                "Article $index",
                "https://example.com",
                "https://example.com/profile_image.png",
                "1970-01-01T00:00:00+00:00".toDate()?.toFormattedString()
            )
        },
        isLoading = false
    )
    ArticleScreen(articleUiState = articleUiState)
}
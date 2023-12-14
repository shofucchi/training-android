package io.github.shofucchi.qiita.ui.feature.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.shofucchi.qiita.R
import io.github.shofucchi.qiita.utility.toDate
import io.github.shofucchi.qiita.utility.toFormattedString

@Composable
fun ArticleScreen(articleUiState: ArticleUiState) {
    Scaffold(topBar = { ArticleAppBar() }) {
        ArticleList(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it),
            articles = articleUiState.articles
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleAppBar() {
    TopAppBar(title = { Text(text = "Article") })
}

@Composable
fun ArticleList(modifier: Modifier, articles: List<Article>) {
    Column(modifier = modifier) {
        for (article in articles) {
            ArticleListItem(
                title = article.title,
                profileImageUrl = article.profileImageUrl,
                updatedAt = article.updatedAt ?: stringResource(id = R.string.invalid_updated_at)
            ) {
                println(article.url)
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun ArticleListItem(
    title: String,
    profileImageUrl: String? = null,
    updatedAt: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileImageUrl)
                    .crossfade(true)
                    .build(),
                error = rememberVectorPainter(image = Icons.Default.BrokenImage),
                contentDescription = "Profile Image"
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = title)
                Text(text = updatedAt)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    val articleUiState = ArticleUiState().copy(articles = (0..100).map { index ->
        Article(
            "Article $index",
            "https://example.com",
            "https://example.com/profile_image.png",
            "1970-01-01T00:00:00+00:00".toDate()?.toFormattedString()
        )
    })
    ArticleScreen(articleUiState = articleUiState)
}
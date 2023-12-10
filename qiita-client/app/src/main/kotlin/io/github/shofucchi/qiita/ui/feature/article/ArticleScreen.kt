package io.github.shofucchi.qiita.ui.feature.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArticleScreen(articleViewModel: ArticleViewModel) {
    articleViewModel.fetchArticles()
    val articleUiState = articleViewModel.uiState.collectAsState()
    Scaffold(topBar = { ArticleAppBar() }) {
        ArticleList(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it),
            articles = articleUiState.value.articles
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
            ArticleListItem(title = article.title) {
                println(article.url)
            }
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun ArticleListItem(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    ArticleScreen(articleViewModel = ArticleViewModelFake())
}
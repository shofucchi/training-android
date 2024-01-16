package io.github.shofucchi.qiita.ui.feature.common.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.shofucchi.qiita.R
import io.github.shofucchi.qiita.ui.feature.common.consts.AppBarTitleConst

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(appBarTitleConst: AppBarTitleConst) {
    val appBarTitle = when (appBarTitleConst) {
        AppBarTitleConst.Article -> stringResource(id = R.string.article)
    }
    TopAppBar(title = { Text(text = appBarTitle) })
}
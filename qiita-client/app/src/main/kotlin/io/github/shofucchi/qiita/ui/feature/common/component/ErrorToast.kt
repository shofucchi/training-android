package io.github.shofucchi.qiita.ui.feature.common.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.shofucchi.qiita.R
import io.github.shofucchi.qiita.ui.feature.article.ErrorReason

@Composable
fun ErrorToast(context: Context, errorReason: ErrorReason) {
    Toast.makeText(
        context,
        when (errorReason) {
            ErrorReason.NETWORK_ERROR -> stringResource(id = R.string.network_error)
            ErrorReason.HTTP_ERROR -> stringResource(id = R.string.http_error)
            ErrorReason.UNKNOWN_ERROR -> stringResource(id = R.string.unknown_error)
        },
        Toast.LENGTH_SHORT
    ).show()
}

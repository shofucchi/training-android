package io.github.shofucchi.qiita.data

class QiitaLocalDataSource {
    val articles = (0..100).map { index ->
        QiitaApiModel(
            "Article $index",
            "https://example.com"
        )
    }
}
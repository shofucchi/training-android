package io.github.shofucchi.qiita.data

class QiitaRepository(private val qiitaLocalDataSource: QiitaLocalDataSource) {
    fun fetchArticles() = this.qiitaLocalDataSource.articles
}
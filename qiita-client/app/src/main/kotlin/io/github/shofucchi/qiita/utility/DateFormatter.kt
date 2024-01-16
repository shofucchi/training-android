package io.github.shofucchi.qiita.utility

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedString(pattern: String = "yyyy/MM/dd", locale: Locale = Locale.US): String {
    return try {
        SimpleDateFormat(pattern, locale).format(this)
    } catch (e: IllegalArgumentException) {
        SimpleDateFormat("yyyy/MM/dd", locale).format(this)
    }
}
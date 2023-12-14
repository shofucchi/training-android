package io.github.shofucchi.qiita.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(pattern: String = "yyyy-MM-dd'T'HH:mm:ssXXX", locale: Locale = Locale.US): Date? {
    val dateFormat = try {
        SimpleDateFormat(pattern, locale)
    } catch (e: IllegalArgumentException) {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", locale)
    }
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}
package io.github.shofucchi.qiita.utility

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringConverterTest {
    @Test
    fun testToDate() {
        val date = "1970-01-01T00:00:00+09:00".toDate()
        assertThat(date).isNotNull()
        assertThat(date.toString()).isEqualTo("Thu Jan 01 00:00:00 JST 1970")
    }

    @Test
    fun testToDateWithInvalidPattern() {
        val date = "1970-01-01T00:00:00+09:00".toDate("invalid pattern")
        assertThat(date).isNotNull()
        assertThat(date.toString()).isEqualTo("Thu Jan 01 00:00:00 JST 1970")
    }

    @Test
    fun testToDateWithInvalidString() {
        val date = "invalid string".toDate()
        assertThat(date).isNull()
    }
}
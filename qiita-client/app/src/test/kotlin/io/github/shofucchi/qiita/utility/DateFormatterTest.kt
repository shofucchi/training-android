package io.github.shofucchi.qiita.utility

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.Date

class DateFormatterTest {
    @Test
    fun testToFormattedDateYYYYMMDD() {
        val date = Date(0).toFormattedString()
        assertThat(date).isEqualTo("1970/01/01")
    }

    @Test
    fun testToFormattedDateYYYYMMDDHHMMSS() {
        val date = Date(0).toFormattedString("yyyy/MM/dd HH:mm:ss")
        assertThat(date).isEqualTo("1970/01/01 09:00:00")
    }

    @Test
    fun testToInvalidFormattedDate() {
        val date = Date(0).toFormattedString("invalid")
        assertThat(date).isEqualTo("1970/01/01")
    }
}
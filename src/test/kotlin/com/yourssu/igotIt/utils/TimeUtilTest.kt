package com.yourssu.igotIt.utils

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TimeUtilTest {

    @Test
    fun calculateDdayForLocalDateTimeTest() {
        val startDate = LocalDateTime.now().plusDays(1)
        val endDate = LocalDateTime.now().plusDays(30)

        println(ChronoUnit.DAYS.between(startDate, endDate))

    }
}
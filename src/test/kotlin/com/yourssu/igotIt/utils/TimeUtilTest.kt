package com.yourssu.igotIt.utils

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TimeUtilTest {

    @Test
    fun calculateDdayForLocalDateTimeTest() {
        val startDate = LocalDateTime.now()
        val endDate = LocalDateTime.now().plusDays(30)

        println(ChronoUnit.DAYS.between(startDate, endDate))
    }

    @Test
    fun calculateDdayForLocalDateTimeTest2() {
        val startDate = LocalDateTime.of(2023,5,4, 1,27,20)
        val endDate = startDate.plusDays(3)
        val now = LocalDateTime.now()

        println(ChronoUnit.DAYS.between(endDate, now))
    }
}
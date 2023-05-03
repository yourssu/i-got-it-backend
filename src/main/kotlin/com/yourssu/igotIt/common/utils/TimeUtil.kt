package com.yourssu.igotIt.common.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TimeUtil {
    companion object {
        fun calculateDday(startDate: LocalDateTime, period: Int): Int {
            val endDate = startDate.plusDays(period.toLong())
            val now = LocalDateTime.now()
            return ChronoUnit.DAYS.between(now, endDate).toInt()
        }
    }
}
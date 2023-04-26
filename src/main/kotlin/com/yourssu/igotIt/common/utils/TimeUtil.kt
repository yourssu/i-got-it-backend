package com.yourssu.igotIt.common.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class TimeUtil {
    companion object {
        fun calculateDday(startDate: LocalDateTime, period: Int): Int {
            val endDate = startDate.plusDays(period.toLong())
            val now = LocalDateTime.now()
            val dday = ChronoUnit.DAYS.between(now, endDate).toInt()

            return if(dday > 0) dday else 0
        }
    }
}
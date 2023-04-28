package com.yourssu.igotIt.scheduler.mail

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MailScheduler(
    private val mailHourJob: MailHourJob
) {

    @Scheduled(cron = "0 0 0/1 * * *")
    fun run() {
        mailHourJob.run()
    }
}
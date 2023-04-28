package com.yourssu.igotIt.scheduler.mail

import com.yourssu.igotIt.mail.service.MailService
import com.yourssu.igotIt.mail.client.resolution.ResolutionMailClient
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@EnableScheduling
@Component
class MailHourJob(
    private val mailService: MailService,
    private val resolutionMailClient: ResolutionMailClient
) {

    fun run() {
        resolutionMailClient.generateRequest()
            .map { mailService.sendMail(it) }
    }
}
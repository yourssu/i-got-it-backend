package com.yourssu.igotIt.scheduler.mail

import com.yourssu.igotIt.mail.service.MailService
import com.yourssu.igotIt.mail.client.resolution.ResolutionMailClient
import org.springframework.stereotype.Component

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
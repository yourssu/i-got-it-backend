package com.yourssu.igotIt.mail

import com.yourssu.igotIt.mail.client.resolution.ResolutionMailClient
import com.yourssu.igotIt.mail.service.MailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController(
    private val mailService: MailService,
    private val resolutionMailClient: ResolutionMailClient
) {

    @PostMapping("/api/v1/mail/test")
    fun test() {
        resolutionMailClient.generateRequest()
            .map { mailService.sendMail(it) }
    }
}
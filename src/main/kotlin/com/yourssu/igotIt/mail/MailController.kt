package com.yourssu.igotIt.mail

import com.yourssu.igotIt.resolution.domain.ResolutionQueryHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MailController(
    private val mailService: MailService,
    private val resolutionQueryHandler: ResolutionQueryHandler
) {

    @PostMapping("/api/v1/mail/test")
    fun test() {
        val resolution = resolutionQueryHandler.findById(1L)
        val request = MailPostRequest(
            email = "lhjae3@naver.com",
            subject = "[ 아가릿! ] 당신의 결심은 아가릿인가요, igotit인가요?",
            resolution = resolution,
            user = resolution.user,
            type = "email"
        )
        mailService.sendMail(request)
    }
}
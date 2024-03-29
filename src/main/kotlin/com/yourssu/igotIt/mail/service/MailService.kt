package com.yourssu.igotIt.mail.service

import com.yourssu.igotIt.mail.domain.MailRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class MailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine,

    @Value("\${server.igotit.url}")
    private val baseUrl: String
) {

    fun sendMail(dto: MailRequest) {
        val message = javaMailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message, false, "UTF-8")
        messageHelper.setTo(dto.email())
        messageHelper.setSubject(dto.subject())
        messageHelper.setText(setContext(dto), true)
        javaMailSender.send(message)
    }

    private fun setContext(dto: MailRequest): String {
        return templateEngine.process(dto.type(), dto.createContext(baseUrl))
    }
}
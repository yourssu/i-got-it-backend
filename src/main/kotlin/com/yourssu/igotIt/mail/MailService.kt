package com.yourssu.igotIt.mail

import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.user.domain.User
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class MailService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine
) {

    /**
     * scheduler -> 결심 상태(마감) 업데이트 ->
     */
    fun sendMail(dto: MailPostRequest) {
        val message = javaMailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message, false, "UTF-8")
        messageHelper.setTo(dto.email)
        messageHelper.setSubject(dto.subject)
        messageHelper.setText(setContext(dto.user, dto.resolution, dto.type), true)
        javaMailSender.send(message)
    }

    /**
     * 결심 기간: resolution.period
     * 결심 내용: resolution.content
     * 나에게 편지 쓴 내용: 은 현재 구현 불가
     * -> letter는 userId를 갖고 있지 않으므로, resolutionId로 해당 결심의 쪽지를 찾아도 자신이 쓴 쪽지를 찾을 수 없음
     * 사용자 이름: resolution.user.nickname
     * TODO: resolution에 link 컬럼 저장 후 불러오기
     */
    private fun setContext(user: User, resolution: Resolution, type: String): String {
        return Context().apply {
            setVariable("period", resolution.period)
            setVariable("content", resolution.content)
            setVariable("nickname", user.nickname)
            setVariable("link", "https://letter.yourssu.com")
        }.run { templateEngine.process(type, this) }
    }
}
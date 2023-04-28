package com.yourssu.igotIt.mail

import com.yourssu.igotIt.mail.domain.MailRequest
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.user.domain.User
import org.thymeleaf.context.Context

private const val subject = "[ 아가릿! ] 당신의 결심은 아가릿인가요, igotit인가요?"
private const val type = "email"

data class ResolutionDoneMailRequest(
    val email: String,
    val resolution: Resolution,
    val user: User,
) : MailRequest {

    override fun email(): String = email
    override fun subject(): String = subject
    override fun type(): String = type

    /**
     * 결심 기간: resolution.period
     * 결심 내용: resolution.content
     * 나에게 편지 쓴 내용: 은 현재 구현 불가
     * -> letter는 userId를 갖고 있지 않으므로, resolutionId로 해당 결심의 쪽지를 찾아도 자신이 쓴 쪽지를 찾을 수 없음
     * 사용자 이름: resolution.user.nickname
     * TODO: resolution에 link 컬럼 저장 후 불러오기
     */
    override fun createContext(): Context {
        return Context().apply {
            setVariable("period", resolution.period)
            setVariable("content", resolution.content)
            setVariable("nickname", user.nickname)
            setVariable("link", "https://letter.yourssu.com/")
        }
    }
}
package com.yourssu.igotIt.auth.infra.kakao

import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.domain.OAuthProvider
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

data class KakaoLoginRequest(
    val code: String

) : OAuthLoginRequest {

    override fun oAuthProvider(): OAuthProvider {
        return OAuthProvider.KAKAO
    }

    override fun makeBody(): MultiValueMap<String, String> {
        val body = LinkedMultiValueMap<String, String>()
        body.add("code", code)
        return body
    }
}
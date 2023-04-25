package com.yourssu.igotIt.auth.infra.kakao

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthProvider

class KakaoInfo @JsonCreator constructor(
    @field:JsonProperty(value = "kakao_account")
    var kakaoAccount: KakaoAccount

) : OAuthInfo {

    class KakaoAccount (
        @field:JsonProperty(value = "email")
        var email: String
    )

    override fun getEmail(): String {
        return kakaoAccount.email
    }

    override fun getOAuthProvider(): OAuthProvider {
        return OAuthProvider.KAKAO
    }
}
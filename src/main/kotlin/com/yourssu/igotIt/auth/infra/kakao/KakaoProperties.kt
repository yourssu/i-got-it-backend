package com.yourssu.igotIt.auth.infra.kakao

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.kakao")
data class KakaoProperties(
    val clientId: String,
    val url: UrlProperties
) {
    data class UrlProperties (
        val auth: String,
        val api: String
    )
}
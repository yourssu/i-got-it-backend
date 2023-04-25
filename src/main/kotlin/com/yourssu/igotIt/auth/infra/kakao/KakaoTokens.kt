package com.yourssu.igotIt.auth.infra.kakao

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoTokens(
    @field:JsonProperty("access_token")
    val accessToken: String,

    @field:JsonProperty("token_type")
    val tokenType: String,

    @field:JsonProperty("refresh_token")
    val refreshToken: String,

    @field:JsonProperty("expires_in")
    val expiresIn: String,

    @field:JsonProperty("refresh_token_expires_in")
    val refreshTokenExpiresIn: String
)
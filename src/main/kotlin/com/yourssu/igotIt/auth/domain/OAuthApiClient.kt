package com.yourssu.igotIt.auth.domain

interface OAuthApiClient {
    fun oAuthProvider(): OAuthProvider
    fun requestAccessToken(dto: OAuthLoginRequest): String
    fun requestOauthInfo(accessToken: String): OAuthInfo
}
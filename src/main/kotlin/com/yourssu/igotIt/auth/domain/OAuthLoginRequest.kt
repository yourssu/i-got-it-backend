package com.yourssu.igotIt.auth.domain

import org.springframework.util.MultiValueMap

interface OAuthLoginRequest {
    fun oAuthProvider(): OAuthProvider
    fun makeBody(): MultiValueMap<String, String>
}
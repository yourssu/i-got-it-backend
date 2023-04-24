package com.yourssu.igotIt.auth.domain

interface OAuthInfo {
    fun getEmail(): String
    fun getOAuthProvider(): OAuthProvider
}
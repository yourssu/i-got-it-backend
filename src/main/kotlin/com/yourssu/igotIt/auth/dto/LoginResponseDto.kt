package com.yourssu.igotIt.auth.dto

data class LoginResponseDto(
    val userId: Long,
    val isNewUser: Boolean,
    val accessToken: String
)
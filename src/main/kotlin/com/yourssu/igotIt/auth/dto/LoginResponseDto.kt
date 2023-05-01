package com.yourssu.igotIt.auth.dto

data class LoginResponseDto(
    val userId: Long,
    val nickname: String?,
    val resolutionId: String?,
    val accessToken: String
)
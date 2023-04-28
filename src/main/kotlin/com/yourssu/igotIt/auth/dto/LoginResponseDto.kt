package com.yourssu.igotIt.auth.dto

data class LoginResponseDto(
    val userId: Long,
    val nickname: String?,
    val resolutionId: Long?,
    val accessToken: String
)
package com.yourssu.igotIt.resolution.dto

import com.yourssu.igotIt.resolution.domain.vo.Status

data class ResolutionGetResponse(
    val nickname: String,
    val content: String,
    val dday: Int,
    val status: Status
)
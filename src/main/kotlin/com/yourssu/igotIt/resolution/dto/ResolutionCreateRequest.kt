package com.yourssu.igotIt.resolution.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ResolutionCreateRequest(
    @field:NotNull(message = "결심 기간은 필수입니다.")
    val period: Int,

    @field:NotNull(message = "결심 내용은 필수입니다.")
    @field:Size(max = 33)
    val content: String,

    @field:NotNull(message = "자신에게 편지작성은 필수입니다.")
    @field:Size(max = 133)
    val letter: String,

    @field:Email(message = "이메일 형식이 아닙니다.")
    val mail: String? = null
)
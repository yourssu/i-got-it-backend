package com.yourssu.igotIt.resolution.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
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

    @field:Pattern(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}",
        message = "올바르지 않은 이메일 형식입니다.")
    val mail: String? = null
)
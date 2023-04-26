package com.yourssu.igotIt.letter.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class LetterCreateRequest(
    @field:NotNull
    @field:Size(max = 3)
    val nickname: String,

    @field:NotNull
    @field:Size(max = 133)
    val content: String
)
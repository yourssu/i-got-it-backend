package com.yourssu.igotIt.auth.dto

import javax.validation.constraints.Size

class LoginInfoRequestDto(
    @field:Size(max = 3, message = "닉네임은 최대 3자까지 입력가능합니다.")
    val nickname: String
)
package com.yourssu.igotIt.mail

import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.user.domain.User

data class MailPostRequest(
    val email: String,
    val subject: String,
    val resolution: Resolution,
    val user: User,
    val type: String,
)
package com.yourssu.igotIt.letter.dto

data class LetterGetResponse(
    val isLocked: Boolean,
    val letters: MutableList<LetterDto>
) {
    data class LetterDto(
        val nickname: String,
        val content: String
    )
}
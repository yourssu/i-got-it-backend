package com.yourssu.igotIt.letter.dto

data class LetterGetResponse(
    val letters: MutableList<LetterDto>
) {
    data class LetterDto(
        val nickname: String,
        val content: String
    )
}
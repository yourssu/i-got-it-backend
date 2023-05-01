package com.yourssu.igotIt.letter.dto

data class LetterGetResponse(
    val isLocked: Boolean,
    val letters: MutableList<LetterDto>,
    val isDeleted: Boolean
) {
    data class LetterDto(
        val letterId: Long,
        val nickname: String,
        val content: String
    )

    companion object {
        fun generateEmpty(): LetterGetResponse {
            return LetterGetResponse(
                isLocked = true,
                letters = mutableListOf(),
                isDeleted = true
            )
        }
    }
}
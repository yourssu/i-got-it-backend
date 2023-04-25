package com.yourssu.igotIt.letter.domain

import org.springframework.stereotype.Repository

@Repository
class LetterQueryHandlerImpl(
    private val letterRepository: LetterRepository
) : LetterQueryHandler {

    override fun findById(id: Long): Letter {
        return letterRepository.findById(id)
            .orElseThrow { RuntimeException("존재하지 않는 letter 입니다.") }
    }
}
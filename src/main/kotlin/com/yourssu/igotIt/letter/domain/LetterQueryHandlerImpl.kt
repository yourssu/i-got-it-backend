package com.yourssu.igotIt.letter.domain

import com.yourssu.igotIt.letter.exception.LetterNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class LetterQueryHandlerImpl(
    private val letterRepository: LetterRepository
) : LetterQueryHandler {

    override fun findById(id: Long): Letter {
        return letterRepository.findByIdOrNull(id)
            ?: throw LetterNotFoundException("존재하지 않는 쪽지입니다.")
    }
}
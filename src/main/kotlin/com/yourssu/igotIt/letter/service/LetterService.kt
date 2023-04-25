package com.yourssu.igotIt.letter.service

import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.letter.domain.LetterRepository
import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.resolution.domain.ResolutionQueryHandler
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterService(
    private val letterRepository: LetterRepository,
    private val resolutionQueryHandler: ResolutionQueryHandler
) {

    @Transactional
    fun create(dto: LetterCreateRequest, resolutionId: Long): LetterCreateResponse {
        val resolution = resolutionQueryHandler.findById(resolutionId)
        val letter = with(dto) {
            Letter(
                nickname = nickname,
                content = content,
                resolution = resolution
            )
        }.run { letterRepository.save(this) }

        return LetterCreateResponse(letter.id!!)
    }
}
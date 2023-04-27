package com.yourssu.igotIt.letter.service

import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.letter.domain.LetterRepository
import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.letter.dto.LetterGetResponse
import com.yourssu.igotIt.resolution.domain.Resolution
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

    fun createLetterForMe(resolution: Resolution, content: String, nickname: String) {
        Letter(
            nickname = nickname,
            content = content,
            resolution = resolution
        ).run { letterRepository.save(this) }
    }

    // TODO: userId와 결심상태에 따라 잠금상태인지 아닌지 boolean 값 LetterGetResponse에 추가
    fun get(resolutionId: Long): LetterGetResponse {
        val resolution = resolutionQueryHandler.findById(resolutionId)
        val letters = letterRepository.findAllByResolution(resolution)
            .map { letter ->  with(letter) {
                LetterGetResponse.LetterDto(
                    nickname = nickname,
                    content = content
                ) }
            }.toMutableList()

        return LetterGetResponse(letters)
    }
}
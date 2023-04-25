package com.yourssu.igotIt.resolution.service

import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.letter.domain.LetterQueryHandler
import com.yourssu.igotIt.letter.domain.LetterRepository
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import com.yourssu.igotIt.resolution.dto.ResolutionCreateRequest
import com.yourssu.igotIt.resolution.dto.ResolutionCreateResponse
import com.yourssu.igotIt.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResolutionService(
    private val resolutionRepository: ResolutionRepository,
    private val letterRepository: LetterRepository
) {

    @Transactional
    fun create(dto: ResolutionCreateRequest, user: User): ResolutionCreateResponse {
        val resolution = with(dto) {
            Resolution(
                period = period,
                content = content,
                email = mail,
                user = user
            )
        }.run { resolutionRepository.save(this) }

        createLetter(resolution, dto.letter, user.nickname!!)

        return ResolutionCreateResponse(resolution.id!!)
    }

    private fun createLetter(resolution: Resolution, content: String, nickname: String) {
        Letter(
            nickname = nickname,
            content = content,
            resolution = resolution
        ).run { letterRepository.save(this) }
    }
}
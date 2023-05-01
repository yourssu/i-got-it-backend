package com.yourssu.igotIt.letter.service

import com.yourssu.igotIt.letter.domain.Letter
import com.yourssu.igotIt.letter.domain.LetterRepository
import com.yourssu.igotIt.letter.dto.LetterCreateRequest
import com.yourssu.igotIt.letter.dto.LetterCreateResponse
import com.yourssu.igotIt.letter.dto.LetterGetResponse
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.resolution.domain.ResolutionQueryHandler
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import com.yourssu.igotIt.user.domain.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LetterService(
    private val letterRepository: LetterRepository,
    private val resolutionQueryHandler: ResolutionQueryHandler,
    private val resolutionRepository: ResolutionRepository
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

    @Transactional(readOnly = true)
    fun get(resolutionId: Long, currentUserId: Long?): LetterGetResponse {
        val resolution = resolutionRepository.findByIdOrNull(resolutionId)
            ?: return LetterGetResponse.generateEmpty()

        val isLocked = isLocked(currentUserId, resolution)
        val letters = letterRepository.findAllByResolution(resolution)
            .map { with(it) {
                LetterGetResponse.LetterDto(
                    letterId = id!!,
                    nickname = nickname,
                    content = content
                ) }
            }.toMutableList()

        return LetterGetResponse(isLocked, letters, false)
    }

    private fun isLocked(userId: Long?, resolution: Resolution): Boolean {
        if (!resolution.status.isDone()) {
            return true
        }
        return !isResolutionWriter(userId, resolution)
    }

    private fun isResolutionWriter(userId: Long?, resolution: Resolution): Boolean {
        return (userId != null && userId == resolution.user.id)
    }

    @Transactional
    fun delete(resolutionId: Long, letterId: Long, user: User) {
        val resolution = resolutionQueryHandler.findById(resolutionId)
        if (!isResolutionWriter(user.id, resolution)) {
            throw RuntimeException("결심 생성자만 쪽지를 삭제할 수 있습니다.")
        }

        letterRepository.deleteById(letterId)
    }
}
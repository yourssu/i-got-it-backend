package com.yourssu.igotIt.resolution.service

import com.yourssu.igotIt.common.utils.TimeUtil
import com.yourssu.igotIt.letter.service.LetterService
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.resolution.domain.ResolutionQueryHandler
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import com.yourssu.igotIt.resolution.domain.vo.Status
import com.yourssu.igotIt.resolution.dto.ResolutionCreateRequest
import com.yourssu.igotIt.resolution.dto.ResolutionCreateResponse
import com.yourssu.igotIt.resolution.dto.ResolutionGetResponse
import com.yourssu.igotIt.resolution.exception.ResolutionInvalidAuthorizationException
import com.yourssu.igotIt.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ResolutionService(
    private val resolutionRepository: ResolutionRepository,
    private val resolutionQueryHandler: ResolutionQueryHandler,
    private val letterService: LetterService
) {

    @Transactional
    fun create(dto: ResolutionCreateRequest, user: User): ResolutionCreateResponse {
        resolutionRepository.findByUser(user)?.let {
            return ResolutionCreateResponse(true, it.uniqueId)
        }

        val resolution = createResolution(dto, user);
        return ResolutionCreateResponse(
            isExisted = false,
            resolutionId = resolution.uniqueId
        )
    }

    fun createResolution(dto: ResolutionCreateRequest, user: User): Resolution {
        val resolution = with(dto) {
            Resolution(
                period = period,
                content = content,
                status = Status.INPROGRESS,
                email = mail,
                uniqueId = generateLink(),
                user = user
            )
        }.run { resolutionRepository.save(this) }

        letterService.createLetterForMe(resolution, dto.letter, user.nickname!!)
        return resolution;
    }

    private fun generateLink(): String {
        return UUID.randomUUID().toString()
    }

    @Transactional(readOnly = true)
    fun get(resolutionUniqueId: String): ResolutionGetResponse {
        val resolution = resolutionRepository.findByUniqueId(resolutionUniqueId)
            ?: return ResolutionGetResponse.generateEmpty()

        val dday = TimeUtil.calculateDday(resolution.createdAt!!, resolution.period)
        return with(resolution) {
            ResolutionGetResponse(
                userId = user.id!!,
                nickname = user.nickname!!,
                content = content,
                dday = dday + 1,
                status = status,
                isDeleted = false
            )
        }
    }

    @Transactional
    fun delete(resolutionUniqueId: String, user: User) {
        val resolution = resolutionQueryHandler.findByUniqueId(resolutionUniqueId)
        if (!checkPermission(resolution, user)) {
            throw ResolutionInvalidAuthorizationException("결심 작성자만 삭제 가능합니다.")
        }
        resolutionRepository.deleteById(resolution.id!!)
    }

    private fun checkPermission(resolution: Resolution, user: User): Boolean {
        return resolution.user.id == user.id
    }
}
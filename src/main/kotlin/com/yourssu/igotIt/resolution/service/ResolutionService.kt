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
import com.yourssu.igotIt.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResolutionService(
    private val resolutionRepository: ResolutionRepository,
    private val resolutionQueryHandler: ResolutionQueryHandler,
    private val letterService: LetterService
) {

    @Transactional
    fun create(dto: ResolutionCreateRequest, user: User): ResolutionCreateResponse {
        val resolution = with(dto) {
            Resolution(
                period = period,
                content = content,
                status = Status.INPROGRESS,
                email = mail,
                user = user
            )
        }.run { resolutionRepository.save(this) }

        letterService.createLetterForMe(resolution, dto.letter, user.nickname!!)

        return ResolutionCreateResponse(resolution.id!!)
    }

    @Transactional(readOnly = true)
    fun get(resolutionId: Long): ResolutionGetResponse {
        val resolution = resolutionQueryHandler.findById(resolutionId)
        val dday = TimeUtil.calculateDday(resolution.createdAt!!, resolution.period)

        return with(resolution) {
            ResolutionGetResponse(
                userId = user.id!!,
                nickname = user.nickname!!,
                content = content,
                dday = dday,
                status = status
            )
        }
    }

    fun delete(resolutionId: Long, user: User) {
        val resolution = resolutionQueryHandler.findById(resolutionId)
        if (!checkPermission(resolution, user)) {
            throw RuntimeException("결심 작성자만 삭제 가능합니다.")
        }
        resolutionRepository.deleteById(resolutionId)
    }

    private fun checkPermission(resolution: Resolution, user: User): Boolean {
        return resolution.user.id == user.id
    }
}
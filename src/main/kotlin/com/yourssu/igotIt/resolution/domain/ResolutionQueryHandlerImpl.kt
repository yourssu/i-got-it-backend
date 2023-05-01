package com.yourssu.igotIt.resolution.domain

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ResolutionQueryHandlerImpl(
    private val resolutionRepository: ResolutionRepository
): ResolutionQueryHandler {

    override fun findById(id: Long): Resolution {
        return resolutionRepository.findByIdOrNull(id)
            ?: throw RuntimeException("존재하지 않는 resolution 입니다.")
    }

    override fun findByUniqueId(uniqueId: String): Resolution {
        return resolutionRepository.findByUniqueId(uniqueId)
            ?: throw RuntimeException("존재하지 않는 resolution 입니다.")
    }
}
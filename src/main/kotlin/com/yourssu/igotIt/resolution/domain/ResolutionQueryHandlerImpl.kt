package com.yourssu.igotIt.resolution.domain

import org.springframework.stereotype.Repository

@Repository
class ResolutionQueryHandlerImpl(
    private val resolutionRepository: ResolutionRepository
): ResolutionQueryHandler {

    override fun findById(id: Long): Resolution {
        return resolutionRepository.findById(id)
            .orElseThrow{ RuntimeException("존재하지 않는 resolution 입니다.") }
    }
}
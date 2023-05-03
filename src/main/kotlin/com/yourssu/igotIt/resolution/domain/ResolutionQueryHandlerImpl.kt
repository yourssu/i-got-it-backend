package com.yourssu.igotIt.resolution.domain

import com.yourssu.igotIt.resolution.exception.ResolutionNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ResolutionQueryHandlerImpl(
    private val resolutionRepository: ResolutionRepository
): ResolutionQueryHandler {

    override fun findById(id: Long): Resolution {
        return resolutionRepository.findByIdOrNull(id)
            ?: throw ResolutionNotFoundException("존재하지 않는 결심입니다.")
    }

    override fun findByUniqueId(uniqueId: String): Resolution {
        return resolutionRepository.findByUniqueId(uniqueId)
            ?: throw ResolutionNotFoundException("존재하지 않는 결심입니다.")
    }
}
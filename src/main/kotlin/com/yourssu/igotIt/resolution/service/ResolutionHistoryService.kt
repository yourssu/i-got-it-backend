package com.yourssu.igotIt.resolution.service

import com.yourssu.igotIt.resolution.domain.resolutionHistory.ResolutionHistoryRepository
import com.yourssu.igotIt.resolution.dto.ResolutionHistoryCountResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResolutionHistoryService(
    private val resolutionHistoryRepository: ResolutionHistoryRepository
) {

    @Transactional(readOnly = true)
    fun count(): ResolutionHistoryCountResponse {
        return resolutionHistoryRepository.count()
            .run { ResolutionHistoryCountResponse(this) }
    }
}
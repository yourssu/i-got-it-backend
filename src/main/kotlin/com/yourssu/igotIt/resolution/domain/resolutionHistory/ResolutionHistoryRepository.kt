package com.yourssu.igotIt.resolution.domain.resolutionHistory

import org.springframework.data.jpa.repository.JpaRepository

interface ResolutionHistoryRepository : JpaRepository<ResolutionHistory, Long> {
}
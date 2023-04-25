package com.yourssu.igotIt.resolution.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ResolutionRepository : JpaRepository<Resolution, Long> {
}
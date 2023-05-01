package com.yourssu.igotIt.resolution.domain

import com.yourssu.igotIt.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface ResolutionRepository : JpaRepository<Resolution, Long> {
    fun findByUser(user: User): Resolution?
    fun findByUniqueId(uniqueId: String): Resolution?
}
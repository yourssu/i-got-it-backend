package com.yourssu.igotIt.resolution.domain.resolution

interface ResolutionQueryHandler {
    fun findById(id: Long): Resolution
    fun findByUniqueId(uniqueId: String): Resolution
}
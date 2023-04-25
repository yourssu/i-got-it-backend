package com.yourssu.igotIt.resolution.domain

interface ResolutionQueryHandler {
    fun findById(id: Long): Resolution
}
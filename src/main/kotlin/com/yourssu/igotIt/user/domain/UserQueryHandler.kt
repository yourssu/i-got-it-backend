package com.yourssu.igotIt.user.domain

interface UserQueryHandler {
    fun findById(userId: Long): User
}
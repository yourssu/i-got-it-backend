package com.yourssu.igotIt.user.domain

import org.springframework.stereotype.Repository

@Repository
class UserQueryHandlerImpl(
    private val userRepository: UserRepository

    ) : UserQueryHandler {

    override fun findById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow{ RuntimeException("존재하지 않는 user입니다.") }
    }
}
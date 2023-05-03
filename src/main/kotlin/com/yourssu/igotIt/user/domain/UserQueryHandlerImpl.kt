package com.yourssu.igotIt.user.domain

import com.yourssu.igotIt.user.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserQueryHandlerImpl(
    private val userRepository: UserRepository

    ) : UserQueryHandler {

    override fun findById(userId: Long): User {
        return userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException("존재하지 않는 유저입니다.")
    }
}
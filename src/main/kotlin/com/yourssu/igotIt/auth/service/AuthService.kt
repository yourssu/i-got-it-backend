package com.yourssu.igotIt.auth.service

import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.dto.LoginInfoRequestDto
import com.yourssu.igotIt.auth.dto.LoginResponseDto
import com.yourssu.igotIt.security.jwt.JwtGenerator
import com.yourssu.igotIt.user.domain.User
import com.yourssu.igotIt.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val requestOAuthInfoService: RequestOAuthInfoService,
    private val userRepository: UserRepository,
    private val jwtGenerator: JwtGenerator
) {

    @Transactional
    fun login(dto: OAuthLoginRequest): LoginResponseDto {
        val oAuthInfo = requestOAuthInfoService.request(dto)
        val user = findOrCreateUser(oAuthInfo)
        val token = jwtGenerator.generateAccessToken(user.id!!)

        return generateDto(user, token)
    }

    private fun findOrCreateUser(oAuthInfo: OAuthInfo): User {
        return userRepository.findByEmail(oAuthInfo.getEmail())
             ?: createUser(oAuthInfo)
    }

    private fun createUser(oAuthInfo: OAuthInfo): User {
        return userRepository.save(User(email = oAuthInfo.getEmail()))
    }

    private fun generateDto(user: User, token: String): LoginResponseDto {
        if (user.nickname != null) {
            return LoginResponseDto(user.id!!, false, token)
        }
        return LoginResponseDto(user.id!!, true, token)
    }

    @Transactional
    fun updateInfo(dto: LoginInfoRequestDto, user: User) {
        user.updateNickname(dto.nickname)
        userRepository.save(user)
    }
}
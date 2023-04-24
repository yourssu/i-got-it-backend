package com.yourssu.igotIt.auth.service

import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.dto.LoginInfoRequestDto
import com.yourssu.igotIt.auth.dto.LoginResponseDto
import com.yourssu.igotIt.security.jwt.JwtGenerator
import com.yourssu.igotIt.user.domain.User
import com.yourssu.igotIt.user.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val requestOAuthInfoService: RequestOAuthInfoService,
    private val userRepository: UserRepository,
    private val jwtGenerator: JwtGenerator
) {

    fun login(dto: OAuthLoginRequest): LoginResponseDto {
        val oAuthInfo = requestOAuthInfoService.request(dto)
        val userId = findOrCreateUser(oAuthInfo)
        val token = jwtGenerator.generateAccessToken(userId)

        return LoginResponseDto(userId, token)
    }

    private fun findOrCreateUser(oAuthInfo: OAuthInfo): Long {
        return userRepository.findByEmail(oAuthInfo.getEmail())
            ?.id ?: createUser(oAuthInfo)
    }

    private fun createUser(oAuthInfo: OAuthInfo): Long {
        val user = userRepository.save(User(email = oAuthInfo.getEmail()))
        return user.id!!
    }

    fun updateInfo(dto: LoginInfoRequestDto, user: User) {
        user.updateNickname(dto.nickname)
    }
}
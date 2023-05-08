package com.yourssu.igotIt.auth.service

import com.yourssu.igotIt.auth.domain.OAuthInfo
import com.yourssu.igotIt.auth.domain.OAuthLoginRequest
import com.yourssu.igotIt.auth.dto.AccessTokenRefreshResponse
import com.yourssu.igotIt.auth.dto.LoginInfoRequestDto
import com.yourssu.igotIt.auth.dto.LoginResponseDto
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import com.yourssu.igotIt.security.jwt.JwtGenerator
import com.yourssu.igotIt.user.domain.User
import com.yourssu.igotIt.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val requestOAuthInfoService: RequestOAuthInfoService,
    private val userRepository: UserRepository,
    private val resolutionRepository: ResolutionRepository,
    private val jwtGenerator: JwtGenerator
) {

    @Transactional
    fun login(dto: OAuthLoginRequest): LoginResponseDto {
        val oAuthInfo = requestOAuthInfoService.request(dto)
        val user = findOrCreateUser(oAuthInfo)
        val token = jwtGenerator.generateAccessToken(user.id!!)
        val resolutionUniqueId = resolutionRepository.findByUser(user)?.uniqueId

        return LoginResponseDto(user.id, user.nickname, resolutionUniqueId, token)
    }

    private fun findOrCreateUser(oAuthInfo: OAuthInfo): User {
        return userRepository.findByEmail(oAuthInfo.getEmail())
             ?: createUser(oAuthInfo)
    }

    private fun createUser(oAuthInfo: OAuthInfo): User {
        return User(email = oAuthInfo.getEmail()).run {
            userRepository.save(this)
        }
    }

    @Transactional
    fun updateInfo(dto: LoginInfoRequestDto, user: User) {
        user.updateNickname(dto.nickname)
        userRepository.save(user)
    }

    fun refresh(user: User): AccessTokenRefreshResponse {
        return jwtGenerator.generateAccessToken(user.id!!)
            .run { AccessTokenRefreshResponse(this) }
    }

    @Transactional
    fun withdraw(user: User) {
        deleteChild(user)
        userRepository.delete(user)
    }

    private fun deleteChild(user: User) {
        resolutionRepository.findByUser(user)?.let {
            resolutionRepository.delete(it)
        }
    }
}
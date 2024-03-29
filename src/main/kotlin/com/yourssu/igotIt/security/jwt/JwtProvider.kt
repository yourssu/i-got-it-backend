package com.yourssu.igotIt.security.jwt

import com.yourssu.igotIt.user.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    private val userRepository: UserRepository,
    private val jwtExtractor: JwtExtractor
) {

    fun authenticate(token: String): Authentication {
        val userId = jwtExtractor.extractUserId(token)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw RuntimeException("Jwt 토큰에 해당하는 유저가 존재하지 않습니다.")
        return UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
    }
}
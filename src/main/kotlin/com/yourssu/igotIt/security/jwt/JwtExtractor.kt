package com.yourssu.igotIt.security.jwt

import io.jsonwebtoken.*
import org.springframework.stereotype.Component

@Component
class JwtExtractor(
    private val jwtProperties: JwtProperties
) {

    fun extractUserId(token: String): Long {
        try {
            return extractAllClaims(token)
                .get("userId", Long::class.javaObjectType)
        } catch (e: Exception) {
            throw RuntimeException("Jwt claim에 userId가 존재하지 않습니다.")
        }
    }

    private fun extractAllClaims(token: String): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(jwtProperties.secretKey.toByteArray())
                .parseClaimsJws(token)
                .body
        } catch (expiredJwtException: ExpiredJwtException) {
            throw RuntimeException("만료된 Jwt 토큰입니다.")
        } catch (unsupportedJwtException: UnsupportedJwtException) {
            throw RuntimeException("지원되지 않는 Jwt 토큰입니다.")
        } catch (malformedJwtException: MalformedJwtException) {
            throw RuntimeException("잘못된 형식의 Jwt 토큰입니다.")
        } catch (signatureException: SignatureException) {
            throw RuntimeException("잘못된 Jwt Signature 값입니다.")
        } catch (illegalArgumentException: IllegalArgumentException) {
            throw RuntimeException("질못된 Jwt 헤더 값입니다.")
        }
    }
}
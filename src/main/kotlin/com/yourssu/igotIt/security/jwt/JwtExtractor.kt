package com.yourssu.igotIt.security.jwt

import com.yourssu.igotIt.security.exception.AuthenticateException
import io.jsonwebtoken.*
import org.springframework.stereotype.Component

@Component
class JwtExtractor(
    private val jwtProperties: JwtProperties
) {

    fun extractUserId(token: String): Long {
        return extractAllClaims(token)
            .get("userId", Long::class.javaObjectType)
    }

    private fun extractAllClaims(token: String): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(jwtProperties.secretKey.toByteArray())
                .parseClaimsJws(token)
                .body
        } catch (expiredJwtException: ExpiredJwtException) {
            throw AuthenticateException("만료된 Jwt 토큰입니다.")
        } catch (unsupportedJwtException: UnsupportedJwtException) {
            throw AuthenticateException("지원되지 않는 Jwt 토큰입니다.")
        } catch (malformedJwtException: MalformedJwtException) {
            throw AuthenticateException("잘못된 형식의 Jwt 토큰입니다.")
        } catch (signatureException: SignatureException) {
            throw AuthenticateException("잘못된 Jwt Signature 값입니다.")
        } catch (illegalArgumentException: IllegalArgumentException) {
            throw AuthenticateException("질못된 Jwt 헤더 값입니다.")
        }
    }
}
package com.yourssu.igotIt.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtGenerator(
    private val jwtProperties: JwtProperties
) {

    fun generateAccessToken(userId: Long): String {
        val claims = mutableMapOf<String, Any>().apply {
            put("userId", userId)
        }
        return createToken(claims)
    }

    private fun createToken(claims: MutableMap<String, Any>): String {
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessTokenExp))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey.toByteArray())
            .compact()
    }
}
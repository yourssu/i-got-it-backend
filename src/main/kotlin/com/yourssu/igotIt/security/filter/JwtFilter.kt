package com.yourssu.igotIt.security.filter

import com.yourssu.igotIt.security.jwt.JwtProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private const val AUTHORIZATION_HEADER = "Authorization"

class JwtFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(AUTHORIZATION_HEADER)
            ?: throw RuntimeException("Authorization Header is missing")
        val authentication = jwtProvider.authenticate(token)

        SecurityContextHolder.getContext().apply {
            setAuthentication(authentication)
        }

        filterChain.doFilter(request, response)
    }
}
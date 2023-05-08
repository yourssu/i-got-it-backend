package com.yourssu.igotIt.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.yourssu.igotIt.common.exception.ErrorCode
import com.yourssu.igotIt.common.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: AuthenticationException
    ) {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            error = HttpStatus.UNAUTHORIZED.name,
            code = ErrorCode.DEFAULT.code,
            message = ex.message!!,
            path = request.requestURI.toString()
        )

        with(response) {
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = "UTF-8"
            writer.println(convertJson(errorResponse))
        }
    }

    private fun convertJson(errorResponse: ErrorResponse): String? {
        return ObjectMapper().registerKotlinModule()
            .registerModule(JavaTimeModule())
            .writeValueAsString(errorResponse)
    }
}
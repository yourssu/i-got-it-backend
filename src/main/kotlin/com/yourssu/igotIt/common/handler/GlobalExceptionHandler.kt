package com.yourssu.igotIt.common.handler

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode
import com.yourssu.igotIt.common.response.ErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ApiException::class])
    fun handleApiException(
        ex: ApiException,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return ErrorResponse.toResponseEntity(
            ex.errorCode,
            ex.message,
            httpServletRequest.requestURI.toString()
        )
    }

    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return ErrorResponse(
            status = status.value(),
            error = status.name,
            code = ErrorCode.BAD_REQUEST.code,
            message = ex.bindingResult.fieldError?.defaultMessage ?: "잘못된 형식의 입력 값 입니다.",
            path = httpServletRequest.requestURI.toString()
        ).run { ResponseEntity(this, status) }
    }

    fun handleExceptionInternal(
        ex: Exception,
        headers: HttpHeaders,
        status: HttpStatus,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<Any> {
        return ErrorResponse(
            status = status.value(),
            error = status.name,
            code = ErrorCode.INTERNAL_SERVER.code,
            message = "서버 내부 오류 입니다",
            path = httpServletRequest.requestURI.toString()
        ).run { ResponseEntity(this, status) }
    }
}
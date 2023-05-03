package com.yourssu.igotIt.common.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.yourssu.igotIt.common.exception.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ErrorResponse(
    @field:JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd HH:mm:ss",
        locale = "Asia/Seoul"
    )
    val timestamp: LocalDateTime? = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val code: Int,
    val message: String,
    val path: String
) {
    companion object {
        fun toResponseEntity(errorCode: ErrorCode, message: String, path: String)
                : ResponseEntity<ErrorResponse> {
            return ResponseEntity
                .status(errorCode.status)
                .body(ErrorResponse(
                    status = errorCode.status.value(),
                    error = errorCode.status.name,
                    code = errorCode.code,
                    message = message,
                    path = path
                ))
        }
    }
}
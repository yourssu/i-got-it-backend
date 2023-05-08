package com.yourssu.igotIt.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(
    val status: HttpStatus,
    val code: Int
) {
    // AUTH
    UNAUTHORIZED_USER(UNAUTHORIZED, 1),
    INVALID_TOKEN(UNAUTHORIZED, 2),
    INVALID_AUTHORIZATION_HEADER(UNAUTHORIZED, 3),
    NOT_EXIST_OAUTH_PROVIDER(HttpStatus.BAD_REQUEST, 4),

    // USER
    USER_NOT_FOUND(NOT_FOUND, 5),

    // RESOLUTION
    RESOLUTION_NOT_FOUND(NOT_FOUND, 6),
    RESOLUTION_FORBIDDEN(HttpStatus.FORBIDDEN, 7),

    //LETTER
    LETTER_NOT_FOUND(NOT_FOUND, 8),
    LETTER_FORBIDDEN(FORBIDDEN, 9),

    // DEFAULT
    DEFAULT(INTERNAL_SERVER_ERROR, -1)
    ;
}
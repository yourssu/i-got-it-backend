package com.yourssu.igotIt.common.exception

import org.springframework.web.server.ResponseStatusException

open class ApiException(
    message: String,
    val errorCode: ErrorCode
) : ResponseStatusException(errorCode.status, message)
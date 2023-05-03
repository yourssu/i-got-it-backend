package com.yourssu.igotIt.user.exception

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode

class UserNotFoundException(
    message: String
) : ApiException(message, ErrorCode.USER_NOT_FOUND)
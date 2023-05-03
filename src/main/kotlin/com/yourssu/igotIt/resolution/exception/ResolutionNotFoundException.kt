package com.yourssu.igotIt.resolution.exception

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode

class ResolutionNotFoundException(
    message: String
) : ApiException(message, ErrorCode.RESOLUTION_NOT_FOUND)
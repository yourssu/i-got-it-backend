package com.yourssu.igotIt.resolution.exception

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode

class ResolutionInvalidAuthorizationException(
    message: String
) : ApiException(message, ErrorCode.RESOLUTION_FORBIDDEN)
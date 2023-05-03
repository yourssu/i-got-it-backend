package com.yourssu.igotIt.auth.exception

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode

class NotExistOAuthProviderException(
    message: String
) : ApiException(message, ErrorCode.NOT_EXIST_OAUTH_PROVIDER)
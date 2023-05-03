package com.yourssu.igotIt.letter.exception

import com.yourssu.igotIt.common.exception.ApiException
import com.yourssu.igotIt.common.exception.ErrorCode

class LetterUnAuthorizationException(
    message: String
) : ApiException(message, ErrorCode.LETTER_FORBIDDEN)
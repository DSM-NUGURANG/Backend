package com.dohyeon5626.nugurang.error.exception

import com.dohyeon5626.nugurang.error.ErrorCode

class AlreadyExistsException(reason: String): GlobalException(
    ErrorCode.ALREADY_EXISTS, reason
)
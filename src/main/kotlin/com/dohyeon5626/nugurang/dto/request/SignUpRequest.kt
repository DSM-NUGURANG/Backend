package com.dohyeon5626.nugurang.dto.request

import javax.validation.constraints.Size

class SignUpRequest (
    @Size(min = 1, max = 9)
    val nickname: String,
    @Size(min = 1, max = 64)
    val email: String,
    @Size(min = 8, max = 16)
    val password: String
)
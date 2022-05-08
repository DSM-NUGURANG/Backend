package com.dohyeon5626.nugurang.dto.request

import javax.validation.constraints.Size

class LoginRequest (
    @Size(min = 1, max = 64)
    val email: String,
    @Size(min = 8, max = 16)
    val password: String
)
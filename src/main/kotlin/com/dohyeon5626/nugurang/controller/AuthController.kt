package com.dohyeon5626.nugurang.controller

import com.dohyeon5626.nugurang.dto.request.ChangePasswordRequest
import com.dohyeon5626.nugurang.dto.request.LoginRequest
import com.dohyeon5626.nugurang.dto.request.SignUpRequest
import com.dohyeon5626.nugurang.dto.response.LoginResponse
import com.dohyeon5626.nugurang.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController (
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): LoginResponse {
        return authService.login(request)
    }

    @PostMapping("/sign-up/email")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@Valid @RequestBody request: SignUpRequest) {
        authService.signUp(request)
    }

    @PostMapping("/password-recovery/email")
    @ResponseStatus(HttpStatus.CREATED)
    fun changePassword(@Valid @RequestBody request: ChangePasswordRequest) {
        authService.changePassword(request)
    }
}
package com.dohyeon5626.nugurang.controller

import com.dohyeon5626.nugurang.service.TokenCheckService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class TokenCheckController (
    private val tokenCheckService: TokenCheckService
) {
    @RequestMapping("/check-sign-up")
    fun checkSignUp(@RequestParam("token") token: String): String {
        if (tokenCheckService.checkSignUpToken(token))
            return "Success.html"
        return "SignUpFail.html"
    }

    @RequestMapping("/check-password-recovery")
    fun checkChangePassword(@RequestParam("token") token: String): String {
        if (tokenCheckService.checkChangePasswordToken(token))
            return "Success.html"
        return "PasswordChangeFail.html"
    }
}
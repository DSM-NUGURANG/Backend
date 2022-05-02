package com.dohyeon5626.nugurang.service

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class MailContentService {
    private val signUpTemplate: String
    private val changePasswordTemplate: String

    init {
        signUpTemplate = getResourceByPath("templates/SignUpMail.html")
            .replace("{LINK}", "https://nugurang.herokuapp.com/check-token/sign-up" + "?token={TOKEN}")
        changePasswordTemplate = getResourceByPath("templates/ChangePasswordMail.html")
            .replace("{LINK}", "https://nugurang.herokuapp.com/check-token/sign-up" + "?token={TOKEN}")
    }

    private fun getResourceByPath(path: String): String {
        val bytes = ClassPathResource(path).inputStream.readAllBytes()
        return String(bytes)
    }

    fun createSignUpContent(token: String, email: String): String {
        return signUpTemplate
            .replace("{TOKEN}", token)
    }

    fun createChangePasswordContent(token: String, email: String): String {
        return changePasswordTemplate
            .replace("{TOKEN}", token)
    }
}
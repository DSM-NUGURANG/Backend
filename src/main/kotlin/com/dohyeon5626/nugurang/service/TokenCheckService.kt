package com.dohyeon5626.nugurang.service

import com.dohyeon5626.nugurang.entity.User
import com.dohyeon5626.nugurang.repository.UserRepository
import com.dohyeon5626.nugurang.repository.redis.ChangePasswordCertifyRepository
import com.dohyeon5626.nugurang.repository.redis.SignUpCertifyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TokenCheckService (
    private val userRepository: UserRepository,
    private val signUpCertifyRepository: SignUpCertifyRepository,
    private val changePasswordCertifyRepository: ChangePasswordCertifyRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun checkSignUpToken(token: String): Boolean {
        val signUpCertify = signUpCertifyRepository.findByIdOrNull(token.toLong())
            ?: return false

        val user = User(signUpCertify.password, signUpCertify.nickname, signUpCertify.email)
        userRepository.save(user)
        signUpCertifyRepository.delete(signUpCertify)
        return true
    }

    @Transactional
    fun checkChangePasswordToken(token: String): Boolean {
        val changePasswordCertify = changePasswordCertifyRepository.findByIdOrNull(token.toLong())
            ?: return false

        val user = userRepository.findByEmail(changePasswordCertify.email)
            ?: return false
        user.password = passwordEncoder.encode(changePasswordCertify.password)
        changePasswordCertifyRepository.delete(changePasswordCertify)
        return true
    }
}
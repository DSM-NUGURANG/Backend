package com.dohyeon5626.nugurang.service

import com.dohyeon5626.nugurang.dto.request.ChangePasswordRequest
import com.dohyeon5626.nugurang.dto.request.LoginRequest
import com.dohyeon5626.nugurang.dto.request.SignUpRequest
import com.dohyeon5626.nugurang.dto.response.LoginResponse
import com.dohyeon5626.nugurang.entity.redis.ChangePasswordCertify
import com.dohyeon5626.nugurang.entity.redis.SignUpCertify
import com.dohyeon5626.nugurang.error.exception.AlreadyExistsException
import com.dohyeon5626.nugurang.error.exception.InvalidAuthDataException
import com.dohyeon5626.nugurang.error.exception.NotFoundException
import com.dohyeon5626.nugurang.repository.UserRepository
import com.dohyeon5626.nugurang.repository.redis.ChangePasswordCertifyRepository
import com.dohyeon5626.nugurang.repository.redis.SignUpCertifyRepository
import com.dohyeon5626.nugurang.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val signUpCertifyRepository: SignUpCertifyRepository,
    private val changePasswordCertifyRepository: ChangePasswordCertifyRepository,
    private val mailContentService: MailContentService,
    private val mailSendService: MailSendService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional(readOnly = true)
    fun login(request: LoginRequest): LoginResponse {
        userRepository.findByEmail(request.email)
            ?.takeIf { passwordEncoder.matches(request.password, it.password) }
            ?: throw InvalidAuthDataException("Invalid login data")
        return LoginResponse(jwtTokenProvider.generateAccessToken(request.email))
    }

    @Transactional
    fun signUp(request: SignUpRequest) {
        val email = request.email
        val nickname = request.nickname

        if (userRepository.existsByEmail(email) || signUpCertifyRepository.findByEmail(email) != null)
            throw AlreadyExistsException("Email already exists")

        val signUpCertify = SignUpCertify(email, passwordEncoder.encode(request.password), nickname)
        signUpCertifyRepository.save(signUpCertify)

        val content = mailContentService.createSignUpContent(signUpCertify.id.toString())
        mailSendService.sendMail(email, nickname + "님을 위한 누구랑 회원가입 인증이 도착했습니다", content)
    }

    @Transactional
    fun changePassword(request: ChangePasswordRequest) {
        val email = request.email

        val user = userRepository.findByEmail(email)
            ?: throw NotFoundException("User not found")

        val changePasswordCertify = ChangePasswordCertify(email, request.newPassword)
        changePasswordCertifyRepository.save(changePasswordCertify)

        val content = mailContentService.createChangePasswordContent(changePasswordCertify.id.toString())
        mailSendService.sendMail(email, user.nickname + "님을 위한 누구랑 비밀번호 재설정 인증이 도착했습니다", content)
    }
}
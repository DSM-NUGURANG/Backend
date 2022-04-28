package com.dohyeon5626.nugurang.security.details

import com.dohyeon5626.nugurang.error.exception.InvalidTokenException
import com.dohyeon5626.nugurang.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService (
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        if (!userRepository.existsById(username.toInt()))
            throw InvalidTokenException("\"$username\" is not found")
        return AuthDetails(username)
    }
}
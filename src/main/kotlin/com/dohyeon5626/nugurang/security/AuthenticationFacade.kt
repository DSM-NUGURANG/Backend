package com.dohyeon5626.nugurang.security

import com.dohyeon5626.nugurang.security.details.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationFacade {
    fun getUserId(): Int {
        return (SecurityContextHolder.getContext().authentication.principal as AuthDetails).id.toInt()
    }
}
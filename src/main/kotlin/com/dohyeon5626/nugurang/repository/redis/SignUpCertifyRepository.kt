package com.dohyeon5626.nugurang.repository.redis

import com.dohyeon5626.nugurang.entity.redis.SignUpCertify
import org.springframework.data.repository.CrudRepository

interface SignUpCertifyRepository: CrudRepository<SignUpCertify, Long> {
    fun findByEmail(email: String): SignUpCertify?
}
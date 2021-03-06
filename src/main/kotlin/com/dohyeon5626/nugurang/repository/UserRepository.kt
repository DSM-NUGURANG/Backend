package com.dohyeon5626.nugurang.repository

import com.dohyeon5626.nugurang.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}
package com.dohyeon5626.nugurang.security.details

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthDetails (
    val id: String
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String = ""
    override fun getUsername(): String = ""

    override fun isAccountNonExpired(): Boolean = false
    override fun isAccountNonLocked(): Boolean = false
    override fun isCredentialsNonExpired(): Boolean = false
    override fun isEnabled(): Boolean = false
}
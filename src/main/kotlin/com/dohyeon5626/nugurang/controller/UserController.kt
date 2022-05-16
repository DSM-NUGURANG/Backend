package com.dohyeon5626.nugurang.controller

import com.dohyeon5626.nugurang.dto.response.UserInfoResponse
import com.dohyeon5626.nugurang.dto.response.UserRankResponse
import com.dohyeon5626.nugurang.security.AuthenticationFacade
import com.dohyeon5626.nugurang.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (
    private val userService: UserService,
    private val authenticationFacade: AuthenticationFacade
) {
    @GetMapping("/user/info")
    fun getUserInfo(): UserInfoResponse {
        return userService.getInfo(authenticationFacade.getUserId())
    }

    @GetMapping("/user/rank")
    fun getUserRank(): UserRankResponse {
        return userService.getRank(authenticationFacade.getUserId())
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser() {
        userService.deleteUser(authenticationFacade.getUserId())
    }
}
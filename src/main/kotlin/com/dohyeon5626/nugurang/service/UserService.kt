package com.dohyeon5626.nugurang.service

import com.dohyeon5626.nugurang.dto.response.UserInfoResponse
import com.dohyeon5626.nugurang.dto.response.UserRankResponse
import com.dohyeon5626.nugurang.entity.NugurangScore
import com.dohyeon5626.nugurang.repository.NugurangScoreRepository
import com.dohyeon5626.nugurang.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
    private val nugurangScoreRepository: NugurangScoreRepository
) {
    companion object {
        val comparator : Comparator<NugurangScore> = compareBy { it.score }
    }

    @Transactional(readOnly = true)
    fun getInfo(userId: Int): UserInfoResponse {
        val user = userRepository.findByIdOrNull(userId)!!
        return UserInfoResponse(user.nickname, user.email)
    }

    @Transactional(readOnly = true)
    fun getRank(userId: Int): UserRankResponse {
        val scoreList = nugurangScoreRepository.findAll()
        scoreList.sortedWith(comparator)
        val number = (scoreList.find { it.id == userId })!!.score
        val rank = (number/scoreList.size*100)
        return UserRankResponse(rank, number)
    }
}
package com.dohyeon5626.nugurang.service

import com.dohyeon5626.nugurang.dto.request.PositionRequest
import com.dohyeon5626.nugurang.dto.response.PositionResponse
import com.dohyeon5626.nugurang.dto.response.SuccessResponse
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class MissionService (
    private val asyncService: AsyncService
) {

    private var easyWaitingUser: Int? = null
    private var normalWaitingUser: Int? = null
    private var hardWaitingUser: Int? = null

    private val userPositions: HashMap<Int, PositionRequest> = HashMap()

    private val easyMissions: HashMap<Int, SseEmitter> = HashMap()
    private val normalMissions: HashMap<Int, SseEmitter> = HashMap()
    private val hardMissions: HashMap<Int, SseEmitter> = HashMap()

    fun makeEasyMission(userId: Int, request: PositionRequest): SseEmitter {
        val emitter = SseEmitter(3600000L)
        easyMissions[userId] = emitter
        if (easyWaitingUser != null) {
            val position = userPositions[easyWaitingUser]!!
            val longitude = (position.logitude + request.logitude)/2
            val latitude = (position.latitude + request.latitude)/2
            val response = PositionResponse(longitude, latitude)

            easyMissions[easyWaitingUser]!!.send(response)
            asyncService.send(emitter, response)
            easyWaitingUser = null
        }
        else {
            easyWaitingUser = userId
        }
        return emitter
    }

    fun makeNormalMission(userId: Int, request: PositionRequest): SseEmitter {
        val emitter = SseEmitter(1800000L)
        normalMissions[userId] = emitter
        if (normalWaitingUser != null) {
            val position = userPositions[normalWaitingUser]!!
            val longitude = (position.logitude + request.logitude)/2
            val latitude = (position.latitude + request.latitude)/2
            val response = PositionResponse(longitude, latitude)

            normalMissions[normalWaitingUser]!!.send(response)
            asyncService.send(emitter, response)
            normalWaitingUser = null
        }
        else {
            normalWaitingUser = userId
        }
        return emitter
    }

    fun makeHardMission(userId: Int, request: PositionRequest): SseEmitter {
        val emitter = SseEmitter(600000L)
        hardMissions[userId] = emitter
        if (hardWaitingUser != null) {
            val position = userPositions[hardWaitingUser]!!
            val longitude = (position.logitude + request.logitude)/2
            val latitude = (position.latitude + request.latitude)/2
            val response = PositionResponse(longitude, latitude)

            hardMissions[hardWaitingUser]!!.send(response)
            asyncService.send(emitter, response)
            hardWaitingUser = null
        }
        else {
            hardWaitingUser = userId
        }
        return emitter
    }

    fun finishMission(userId: Int) {
        val easy = easyMissions[userId]
        if (easy != null) {
            easy.send(SuccessResponse(true))
            return
        }

        val normal = normalMissions[userId]
        if (normal != null) {
            normal.send(SuccessResponse(true))
            return
        }

        val hard = hardMissions[userId]
        if (hard != null) {
            hard.send(SuccessResponse(true))
            return
        }
    }

    fun giveUpMission(userId: Int) {
        val easy = easyMissions[userId]
        if (easy != null) {
            easy.send(SuccessResponse(false))
            easyMissions.remove(userId)
            return
        }

        val normal = normalMissions[userId]
        if (normal != null) {
            normal.send(SuccessResponse(false))
            normalMissions.remove(userId)
            return
        }

        val hard = hardMissions[userId]
        if (hard != null) {
            hard.send(SuccessResponse(false))
            hardMissions.remove(userId)
            return
        }
    }

}
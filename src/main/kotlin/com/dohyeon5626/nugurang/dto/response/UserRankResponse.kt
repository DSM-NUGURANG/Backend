package com.dohyeon5626.nugurang.dto.response

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserRankResponse (
    val rank: Int,
    val number: Int
) {
    companion object {
        const val BLACK = "000000"
        const val RED = "FF4343"
        const val ORANGE = "F0A83D"
        const val YELLOW = "D6CE1C"
        const val GREEN = "23D982"
        const val BLUE = "0C75EC"
        const val PURPLE = "A84EEE"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")!!
    }

    private fun getColor(rank: Int): String {
        return when (rank) {
            in 0..5 -> BLACK
            in 6..10 -> RED
            in 11..20 -> ORANGE
            in 21..30 -> YELLOW
            in 31..40 -> GREEN
            in 41..60 -> BLUE
            else -> PURPLE
        }
    }

    val timestamp: String = LocalDate.now().format(formatter)
    val color: String = getColor(rank)
}

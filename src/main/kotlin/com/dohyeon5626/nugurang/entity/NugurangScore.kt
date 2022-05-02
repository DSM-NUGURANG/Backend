package com.dohyeon5626.nugurang.entity

import com.dohyeon5626.nugurang.entity.id.NugurangScoreId
import javax.persistence.*

@Entity
class NugurangScore (
    @EmbeddedId
    val id: NugurangScoreId,
    val score: Int,
) {
    @OneToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    val user: User? = null
}
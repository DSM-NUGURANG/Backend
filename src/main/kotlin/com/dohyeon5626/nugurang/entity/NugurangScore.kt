package com.dohyeon5626.nugurang.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class NugurangScore (
    @Id
    val id: Int,
    val score: Int,
)
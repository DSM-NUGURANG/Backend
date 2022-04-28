package com.dohyeon5626.nugurang.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class NugurangScore (
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    val user: User,
    val score: Int,
): Serializable
package com.dohyeon5626.nugurang.entity.id

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class NugurangScoreId (
    @Column
    val id: Int
): Serializable
package com.mashup.domain.model

import com.mashup.data.dto.local.ChinChin
import java.util.*

data class ChinChinModel(
    val uid: UUID,
    val name: String,
    val date: Date,
    val isFriend: Boolean = false
) {
    fun toChinchin(): ChinChin {
        return ChinChin(uid = uid, name = name, date = date, isFriend = isFriend)
    }
}


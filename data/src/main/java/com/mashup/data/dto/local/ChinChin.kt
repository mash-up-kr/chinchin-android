package com.mashup.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ChinChin(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    val name: String = "",
    val date: Date = Date(),
    val isFriend: Boolean = false,
)

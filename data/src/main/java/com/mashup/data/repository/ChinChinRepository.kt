package com.mashup.data.repository

import com.mashup.data.dto.local.ChinChin
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ChinChinRepository {
    fun getChinChins(): Flow<List<ChinChin>>
    fun getChinChin(uid: UUID): Flow<ChinChin?>
    suspend fun addChinChin(chinchin: ChinChin)
    suspend fun updateChinChin(chinchin: ChinChin)
    suspend fun deleteChinChin(chinchin: ChinChin)
}
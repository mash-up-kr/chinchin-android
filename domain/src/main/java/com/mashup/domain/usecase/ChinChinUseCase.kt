package com.mashup.domain.usecase

import com.mashup.domain.model.ChinChinModel
import kotlinx.coroutines.flow.Flow

interface ChinChinUseCase {
    fun getChinChins(): Flow<List<ChinChinModel>>
    suspend fun addChinChin(name: String)
    suspend fun deleteChinChin(chinchin: ChinChinModel)
    suspend fun updateChinChin(chinchin: ChinChinModel)
}
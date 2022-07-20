package com.mashup.data.repository.impl

import com.mashup.data.dao.ChinChinDao
import com.mashup.data.dto.local.ChinChin
import com.mashup.data.repository.ChinChinRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class ChinChinRepositoryImpl @Inject constructor(private val chinChinDao: ChinChinDao) :
    ChinChinRepository {

    override fun getChinChins(): Flow<List<ChinChin>> = chinChinDao.getChinChins()

    override fun getChinChin(uid: UUID): Flow<ChinChin?> = chinChinDao.getChinChin(uid)

    override suspend fun addChinChin(chinchin: ChinChin) {
        return chinChinDao.insert(chinchin)
    }

    override suspend fun updateChinChin(chinchin: ChinChin) {
        return chinChinDao.update(chinchin)
    }

    override suspend fun deleteChinChin(chinchin: ChinChin) {
        return chinChinDao.delete(chinchin)
    }


}
package com.mashup.data.repository.impl

import androidx.lifecycle.LiveData
import com.mashup.data.dao.ChinChinDao
import com.mashup.data.dto.local.ChinChin
import com.mashup.data.repository.ChinChinRepository
import java.util.*
import javax.inject.Inject

class ChinChinRepositoryImpl @Inject constructor(private val chinChinDao: ChinChinDao) : ChinChinRepository {

    override fun getChinChins(): LiveData<List<ChinChin>> = chinChinDao.getCrimes()

    override fun getChinChin(id: UUID): LiveData<ChinChin?> = chinChinDao.getCrime(id)


}
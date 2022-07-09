package com.mashup.data.repository.impl

import androidx.lifecycle.LiveData
import com.mashup.data.dao.CrimeDao
import com.mashup.data.dto.local.Crime
import com.mashup.data.repository.CrimeRepository
import java.util.*
import javax.inject.Inject

class CrimeRepositoryImpl @Inject constructor(private val crimeDao: CrimeDao) : CrimeRepository {

    override fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    override fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)


}
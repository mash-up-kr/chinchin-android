package com.mashup.data.repository

import androidx.lifecycle.LiveData
import com.mashup.data.dto.local.Crime
import java.util.*

interface CrimeRepository {
    fun getCrimes(): LiveData<List<Crime>>
    fun getCrime(id: UUID): LiveData<Crime?>
}
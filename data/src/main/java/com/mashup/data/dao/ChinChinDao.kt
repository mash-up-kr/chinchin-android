package com.mashup.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.mashup.data.dto.local.ChinChin
import java.util.*


@Dao
interface ChinChinDao {
    @Query("SELECT * FROM chinchin")
    fun getCrimes(): LiveData<List<ChinChin>>

    @Query("SELECT * FROM chinchin WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<ChinChin?>
}
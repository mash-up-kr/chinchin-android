package com.mashup.data.dao

import androidx.room.*
import com.mashup.data.dto.local.ChinChin
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface ChinChinDao {
    @Query("SELECT * FROM chinchin")
    fun getChinChins(): Flow<List<ChinChin>>

    @Query("SELECT * FROM chinchin WHERE uid=(:uid)")
    fun getChinChin(uid: UUID): Flow<ChinChin?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chinchin: ChinChin)

    @Update
    suspend fun update(chinchin: ChinChin)

    @Delete
    suspend fun delete(chinchin: ChinChin)
}
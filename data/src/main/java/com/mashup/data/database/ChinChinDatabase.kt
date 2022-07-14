package com.mashup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mashup.data.dao.ChinChinDao
import com.mashup.data.dto.local.ChinChin

@Database(entities = [ChinChin::class], version = 1)
@TypeConverters(ChinChinTypeConverters::class)
abstract class ChinChinDatabase : RoomDatabase() {
    abstract fun chinChinDao(): ChinChinDao

    companion object {
        const val DATABASE_NAME = "chinchin-database"
    }
}
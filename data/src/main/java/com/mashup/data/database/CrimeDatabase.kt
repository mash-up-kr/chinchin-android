package com.mashup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mashup.data.dao.CrimeDao
import com.mashup.data.dto.local.Crime

@Database(entities = [Crime::class], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao

    companion object {
        const val DATABASE_NAME = "crime-database"
    }
}
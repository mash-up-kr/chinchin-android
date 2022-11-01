package com.mashup.chinchin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mashup.chinchin.data.dao.ChinChinDao
import com.mashup.chinchin.data.dto.local.FriendEntity
import com.mashup.chinchin.data.dto.local.QuestionEntity
import com.mashup.chinchin.data.dto.local.QuestionnaireEntity

@Database(
    entities = [FriendEntity::class, QuestionEntity::class, QuestionnaireEntity::class],
    version = 1
)
@TypeConverters(ChinChinTypeConverters::class)
abstract class ChinChinDatabase : RoomDatabase() {
    abstract fun chinChinDao(): ChinChinDao

    companion object {
        const val DATABASE_NAME = "chinchin-database"
    }
}

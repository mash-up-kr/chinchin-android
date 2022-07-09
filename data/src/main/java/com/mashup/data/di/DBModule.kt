package com.mashup.data.di

import android.content.Context
import androidx.room.Room
import com.mashup.data.dao.ChinChinDao
import com.mashup.data.database.ChinChinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideCrimeDatabases(@ApplicationContext context: Context)
            : ChinChinDatabase = Room.databaseBuilder(
        context,
        ChinChinDatabase::class.java,
        ChinChinDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCrimeDao(chinChinDatabase: ChinChinDatabase): ChinChinDao = chinChinDatabase.chinChinDao()
}
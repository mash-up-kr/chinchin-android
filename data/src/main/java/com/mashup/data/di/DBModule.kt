package com.mashup.data.di

import android.content.Context
import androidx.room.Room
import com.mashup.data.dao.CrimeDao
import com.mashup.data.database.CrimeDatabase
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
            : CrimeDatabase = Room.databaseBuilder(
        context,
        CrimeDatabase::class.java,
        CrimeDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCrimeDao(crimeDatabase: CrimeDatabase): CrimeDao = crimeDatabase.crimeDao()
}
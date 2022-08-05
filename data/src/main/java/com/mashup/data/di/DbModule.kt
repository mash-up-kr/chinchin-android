package com.mashup.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

//    @Provides
//    @Singleton
//    fun provideChinChinDatabases(@ApplicationContext context: Context)
//            : ChinChinDatabase = Room.databaseBuilder(
//        context,
//        ChinChinDatabase::class.java,
//        ChinChinDatabase.DATABASE_NAME
//    ).build()

//    @Provides
//    @Singleton
//    fun provideChinChinDao(chinChinDatabase: ChinChinDatabase): ChinChinDao =
//        chinChinDatabase.chinChinDao()
}

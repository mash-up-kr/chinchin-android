package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteAlarmDataSource
import com.mashup.chinchin.domain.model.Alarm
import com.mashup.chinchin.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val remoteAlarmDataSource: RemoteAlarmDataSource,
): AlarmRepository {
    override suspend fun getAlarms(): List<Alarm> {
        return remoteAlarmDataSource.getAlarms().questionAlarms.map { it.toDomainModel() }
    }

    override suspend fun isAlarmExist(): Boolean = remoteAlarmDataSource.isAlarmExist().isAlarmExist
}

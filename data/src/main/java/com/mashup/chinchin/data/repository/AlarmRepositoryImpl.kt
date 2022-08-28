package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteAlarmDataSource
import com.mashup.chinchin.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val remoteAlarmDataSource: RemoteAlarmDataSource,
): AlarmRepository {
    override suspend fun isAlarmExist(): Boolean = remoteAlarmDataSource.isAlarmExist().isAlarmExist
}

package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.responsebody.IsAlarmResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.UserAlarmResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteAlarmDataSource @Inject constructor(
    private val chinChinService: ChinChinService,
) {
    suspend fun getAlarms(): UserAlarmResponseBody = chinChinService.getAlarms()

    suspend fun isAlarmExist(): IsAlarmResponseBody = chinChinService.isAlarmExist()
}

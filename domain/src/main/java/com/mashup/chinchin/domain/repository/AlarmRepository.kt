package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Alarm

interface AlarmRepository {
    suspend fun getAlarms(): List<Alarm>
    suspend fun isAlarmExist(): Boolean
}

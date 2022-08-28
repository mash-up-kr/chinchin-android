package com.mashup.chinchin.domain.repository

interface AlarmRepository {
    suspend fun isAlarmExist(): Boolean
}

package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Alarm
import com.mashup.chinchin.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
) {
    suspend operator fun invoke(): List<Alarm> = alarmRepository.getAlarms()
}

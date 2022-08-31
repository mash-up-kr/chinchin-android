package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.AlarmRepository
import javax.inject.Inject

class IsAlarmExistUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
) {
    suspend operator fun invoke(): Boolean = alarmRepository.isAlarmExist()
}

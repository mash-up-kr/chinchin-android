package com.mashup.chinchin.presenter.receive_alarm.model

import com.mashup.chinchin.domain.model.Alarm

data class ReceiveAlarmUiModel(
    val questionnaireId: Long,
    val friendName: String,
    val friendProfileUrl: String,
    val alarmDate: Long,
    val alarmType: AlarmType,
)

fun Alarm.toUiModel(): ReceiveAlarmUiModel {
    return ReceiveAlarmUiModel(
        questionnaireId = questionnaireId,
        friendName = friend.name,
        friendProfileUrl = friend.thumbnailImageUrl ?: "",
        alarmDate = createdAt,
        alarmType = AlarmType.fromValue(type)
    )
}

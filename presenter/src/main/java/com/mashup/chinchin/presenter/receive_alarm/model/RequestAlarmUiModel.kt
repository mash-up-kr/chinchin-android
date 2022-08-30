package com.mashup.chinchin.presenter.receive_alarm.model

import com.mashup.chinchin.domain.model.Alarm

/* TODO: 연중이가 고쳐주면 싹 바꿔야됨 이름도 바꾸자 */
data class RequestAlarmUiModel(
    val requestUserName: String,
    val requestUserProfileUrl: String,
    val requestDate: Long,
    val alarmType: AlarmType,
)

fun Alarm.toUiModel(): RequestAlarmUiModel {
    return RequestAlarmUiModel(
        requestUserName = this.friendId.toString(),
        requestUserProfileUrl = this.questionnaireId.toString(),
        requestDate = this.createdAt,
        alarmType = AlarmType.fromValue(this.type)
    )
}

package com.mashup.chinchin.presenter.receive_alarm.model

data class RequestAlarmUiModel(
    val requestUserName: String,
    val requestUserProfileUrl: String,
    val requestDate: Long,
    val alarmType: AlarmType,
)

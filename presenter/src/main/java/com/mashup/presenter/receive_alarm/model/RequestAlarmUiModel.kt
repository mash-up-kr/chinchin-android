package com.mashup.presenter.receive_alarm.model

data class RequestAlarmUiModel(
    val requestUserName: String,
    val requestUserProfileUrl: String,
    val requestDate: Long,
)

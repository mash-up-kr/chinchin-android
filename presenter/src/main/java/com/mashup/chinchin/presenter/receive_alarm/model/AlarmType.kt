package com.mashup.chinchin.presenter.receive_alarm.model

enum class AlarmType(private val value: String) {
    REQUEST("questionRequests"),
    REPLY("completedAnswers");

    companion object {
        fun fromValue(value: String): AlarmType = values().associateBy(AlarmType::value)[value] ?: REQUEST
    }
}

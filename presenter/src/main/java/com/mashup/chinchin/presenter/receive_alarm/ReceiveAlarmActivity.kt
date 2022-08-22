package com.mashup.chinchin.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.receive_alarm.model.AlarmType
import com.mashup.chinchin.presenter.receive_alarm.model.RequestAlarmUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.receive_alarm.EmptyRequestAlarm
import com.mashup.chinchin.presenter.ui.receive_alarm.RequestAlarmList
import com.mashup.chinchin.presenter.ui.receive_alarm.RequestCountText
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme

class ReceiveAlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                ReceiveAlarmScreen(requestAlarmUiModels = initRequestAlarms()) {
                    finish()
                }
            }
        }
    }

    private fun initRequestAlarms(): List<RequestAlarmUiModel> {
        val requestAlarmUiModels = mutableListOf<RequestAlarmUiModel>()
        repeat(20) { index ->
            requestAlarmUiModels.add(
                RequestAlarmUiModel(
                    requestUserName = "경무",
                    requestUserProfileUrl = "https://cdnimg.melon.co.kr/cm2/artistcrop/images/002/61/143/261143_20210325180240_500.jpg?61e575e8653e5920470a38d1482d7312/melon/resize/416/quality/80/optimize",
                    requestDate = index.toLong(),
                    alarmType = if (index % 2 == 0) AlarmType.REQUEST else AlarmType.REPLY,
                )
            )
        }

        return requestAlarmUiModels.toList()
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiveAlarmPreview() {
    ReceiveAlarmScreen()
}

@Composable
fun ReceiveAlarmScreen(
    requestAlarmUiModels: List<RequestAlarmUiModel> = listOf(),
    finishActivity: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(
            title = "알림",
        ) {
            finishActivity()
        }

        RequestCountText(requestAlarmUiModels.size)
        if (requestAlarmUiModels.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyRequestAlarm()
            }
        } else {
            RequestAlarmList(requestAlarmUiModels, modifier = Modifier.padding(top = 7.dp))
        }
    }
}

package com.mashup.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.receive_alarm.model.RequestAlarm
import com.mashup.presenter.ui.receive_alarm.RequestAlarmList
import com.mashup.presenter.ui.receive_alarm.RequestCountText
import com.mashup.presenter.ui.receive_alarm.Toolbar
import com.mashup.presenter.ui.theme.ChinchinTheme

class ReceiveAlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                ReceiveAlarmScreen(requestAlarms = initRequestAlarms()) {
                    finish()
                }
            }
        }
    }

    private fun initRequestAlarms(): List<RequestAlarm> {
        val requestAlarms = mutableListOf<RequestAlarm>()
        repeat(20) { index ->
            requestAlarms.add(
                RequestAlarm(
                    requestUserName = "경무",
                    requestUserProfileUrl = "good",
                    requestDate = index.toLong(),
                )
            )
        }

        return requestAlarms.toList()
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiveAlarmPreview() {
    ReceiveAlarmScreen()
}

@Composable
fun ReceiveAlarmScreen(
    requestAlarms: List<RequestAlarm> = listOf(),
    finishActivity: () -> Unit = {},
) {
    Column {
        Toolbar("나에게 온 요청리스트") {
            finishActivity()
        }

        RequestCountText(requestAlarms.size)
        RequestAlarmList(requestAlarms, modifier = Modifier.padding(top = 7.dp))
    }
}

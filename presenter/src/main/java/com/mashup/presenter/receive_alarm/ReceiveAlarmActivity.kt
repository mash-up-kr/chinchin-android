package com.mashup.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.receive_alarm.model.RequestAlarmUiModel
import com.mashup.presenter.ui.receive_alarm.RequestAlarmList
import com.mashup.presenter.ui.receive_alarm.RequestCountText
import com.mashup.presenter.ui.receive_alarm.Toolbar
import com.mashup.presenter.ui.theme.ChinchinTheme

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
                    requestUserProfileUrl = "good",
                    requestDate = index.toLong(),
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
        Toolbar(
            title = "나에게 온 요청리스트",
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            finishActivity()
        }

        RequestCountText(requestAlarmUiModels.size)
        RequestAlarmList(requestAlarmUiModels, modifier = Modifier.padding(top = 7.dp))
    }
}

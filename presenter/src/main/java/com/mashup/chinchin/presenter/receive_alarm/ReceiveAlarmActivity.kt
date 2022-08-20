package com.mashup.chinchin.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.receive_alarm.model.RequestAlarmUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
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
        ChinChinToolbar(
            title = "나에게 온 요청리스트",
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
                Image(
                    painter = painterResource(id = R.drawable.img_empty_basic),
                    contentDescription = "",
                )
            }
        } else {
            RequestAlarmList(requestAlarmUiModels, modifier = Modifier.padding(top = 7.dp))
        }
    }
}

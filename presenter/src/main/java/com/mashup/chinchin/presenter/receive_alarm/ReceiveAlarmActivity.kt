package com.mashup.chinchin.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.chinchin.presenter.receive_alarm.model.ReceiveAlarmUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.receive_alarm.EmptyRequestAlarm
import com.mashup.chinchin.presenter.ui.receive_alarm.RequestAlarmList
import com.mashup.chinchin.presenter.ui.receive_alarm.RequestCountText
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiveAlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                ReceiveAlarmScreen {
                    finish()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiveAlarmPreview() {
    ReceiveAlarmScreen()
}

@Composable
fun ReceiveAlarmScreen(
    finishActivity: () -> Unit = {},
) {
    val viewModel: ReceiveAlarmViewModel = hiltViewModel()
    viewModel.getAlarms()
    val receiveAlarmUiModels: List<ReceiveAlarmUiModel> = viewModel.alarms.observeAsState().value ?: emptyList()

    StatusBarColor()
    Column {
        ChinChinToolbar(
            title = "알림",
        ) {
            finishActivity()
        }

        RequestCountText(receiveAlarmUiModels.size)
        if (receiveAlarmUiModels.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyRequestAlarm()
            }
        } else {
            RequestAlarmList(receiveAlarmUiModels, modifier = Modifier.padding(top = 7.dp))
        }
    }
}

package com.mashup.presenter.receive_alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.presenter.ui.receive_alarm.Toolbar
import com.mashup.presenter.ui.theme.ChinchinTheme

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
fun ReceiveAlarmScreen(finishActivity: () -> Unit = {}) {
    Column {
        Toolbar("나에게 온 요청리스트") {
            finishActivity()
        }
    }
}

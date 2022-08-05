package com.mashup.chinchin.presenter.send_preference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.White

class SendPreferenceCompleteActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                SendPreferenceCompleteScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendPreferenceCompletePreview() {
    SendPreferenceCompleteScreen()
}

@Composable
fun SendPreferenceCompleteScreen(
    onConfirmButtonClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "보내기 완료!\n홈으로 돌아갈까요?",
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 96.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "친구에게 취향질문보내기를 완료했습니다.\n답변완료되면 알림으로 알려드릴게요 !",
                color = Gray_500,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 19.dp),
                textAlign = TextAlign.Center,
            )

            Image(
                painter = painterResource(id = R.drawable.illust_6_1),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(270.dp)
                    .height(244.dp)
            )
        }

        ChinChinConfirmButton(
            buttonText = "네! 홈으로 돌아갈래요.",
            isEnable = true,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 37.dp),
        ) {
            onConfirmButtonClick()
        }
    }
}

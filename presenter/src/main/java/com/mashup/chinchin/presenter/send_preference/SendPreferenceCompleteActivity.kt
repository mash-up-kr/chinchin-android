package com.mashup.chinchin.presenter.send_preference

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
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
import com.mashup.chinchin.presenter.main.MainActivity
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.theme.*

class SendPreferenceCompleteActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                SendPreferenceCompleteScreen {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = FLAG_ACTIVITY_SINGLE_TOP
                    }
                    finish()
                    startActivity(intent)
                }
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
    StatusBarColor()
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
                text = "친구에게 보내기 완료!",
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 96.dp),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "친구에게 취향답변 보내기를 완료했어요!\n다른 친구의 질문지에도 답변해보세요!",
                color = Gray_500,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 19.dp),
                textAlign = TextAlign.Center,
                lineHeight = 21.sp,
                fontWeight = FontWeight.Light,
            )

            Image(
                painter = painterResource(id = R.drawable.send_complete),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(270.dp)
                    .height(244.dp)
            )
        }

        ChinChinConfirmButton(
            buttonText = "홈으로 돌아갈래요",
            isEnable = true,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 37.dp),
        ) {
            onConfirmButtonClick()
        }
    }
}

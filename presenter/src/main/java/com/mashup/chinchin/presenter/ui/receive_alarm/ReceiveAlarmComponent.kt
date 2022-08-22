package com.mashup.chinchin.presenter.ui.receive_alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.receive_alarm.model.AlarmType
import com.mashup.chinchin.presenter.receive_alarm.model.RequestAlarmUiModel
import com.mashup.chinchin.presenter.ui.theme.*

@Composable
fun RequestCountText(requestCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp)
    ) {
        Text(
            text = "알림",
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = "$requestCount",
            modifier = Modifier.padding(start = 8.dp),
            color = Primary_2,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun RequestAlarmList(requestAlarmUiModels: List<RequestAlarmUiModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(requestAlarmUiModels) { requestAlarm ->
            ReceiveAlarmItem(
                userName = requestAlarm.requestUserName,
                profileUrl = requestAlarm.requestUserProfileUrl,
                modifier = Modifier.padding(horizontal = 24.dp),
                date = requestAlarm.requestDate,
                alarmType = requestAlarm.alarmType,
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun EmptyRequestAlarm() {
    Image(
        painter = painterResource(id = R.drawable.img_empty_basic),
        contentDescription = "",
    )
}

@Preview(showBackground = true)
@Composable
fun ReceiveAlarmItemPreview() {
    ReceiveAlarmItem(
        userName = "경무",
        profileUrl = "good",
        date = 10,
        alarmType = AlarmType.REQUEST,
    )
}

@Composable
fun ReceiveAlarmItem(
    userName: String,
    profileUrl: String,
    date: Long,
    modifier: Modifier = Modifier,
    alarmType: AlarmType,
    onClickButton: () -> Unit = {}
) {
    val titleMessage = when (alarmType) {
        AlarmType.REQUEST -> {
            "이 취향 질문을 요청했어요"
        }
        AlarmType.REPLY -> {
            "이 취향질문에 답변완료했어요"
        }
    }

    Box(
        modifier = modifier
            .height(66.dp)
            .fillMaxWidth()
            .background(color = Secondary_1, shape = RoundedCornerShape(8.dp))
            .clickable { onClickButton() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ReceiveAlarmItemProfileBox(profileUrl = profileUrl, alarmType = alarmType)
            ReceiveAlarmItemContents(userName = "${userName}님", titleMessage = titleMessage, date = date.toString())
        }
    }
}

@Composable
fun ReceiveAlarmItemProfileBox(profileUrl: String, alarmType: AlarmType) {
    val (typeBackgroundColor: Color, typeIconRes: Int) = when (alarmType) {
        AlarmType.REQUEST -> {
            (Primary_2 to R.drawable.icon_question)
        }
        AlarmType.REPLY -> {
            (Gray_700 to R.drawable.icon_smile)
        }
    }

    Box(
        modifier = Modifier.size(58.dp),
    ) {
        AsyncImage(
            model = profileUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .align(Alignment.TopStart),
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(22.dp)
                .background(typeBackgroundColor)
                .align(Alignment.BottomEnd),
        ) {
            Icon(
                painter = painterResource(id = typeIconRes),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center),
                tint = White,
            )
        }
    }
}

@Composable
fun ReceiveAlarmItemContents(userName: String, titleMessage: String, date: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(205.dp)
        ) {
            Row {
                Text(
                    text = userName,
                    fontSize = 12.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = titleMessage,
                    fontSize = 12.sp,
                    color = Black,
                )
            }

            Text(
                text = date,
                fontSize = 12.sp,
                color = Gray_500,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.icon_arrow),
            contentDescription = "",
            tint = Gray_400,
        )
    }
}

package com.mashup.presenter.ui.receive_alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.presenter.receive_alarm.model.RequestAlarm
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Gray
import com.mashup.presenter.ui.theme.Yellow

@Composable
fun Toolbar(title: String, onBackButtonClick: () -> Unit) {
    val modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)

    ConstraintLayout(
        modifier = modifier,
    ) {
        val (iconRef, textRef) = createRefs()

        IconButton(
            onClick = { onBackButtonClick() },
            modifier = Modifier.constrainAs(iconRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back button")
        }

        Text(
            text = title,
            modifier = Modifier.constrainAs(textRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun RequestCountText(requestCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp)
    ) {
        Text(
            text = "요청",
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = "${requestCount}개",
            modifier = Modifier.padding(start = 8.dp),
            color = Yellow,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun RequestAlarmList(requestAlarms: List<RequestAlarm>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(requestAlarms) { requestAlarm ->
            ReceiveAlarmItem(
                userName = requestAlarm.requestUserName,
                profileUrl = requestAlarm.requestUserProfileUrl,
                date = requestAlarm.requestDate,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReceiveAlarmItemPreview() {
    ReceiveAlarmItem(userName = "경무", profileUrl = "good", date = 10)
}

@Composable
fun ReceiveAlarmItem(
    userName: String,
    profileUrl: String,
    date: Long,
    onItemSelected: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(start = 24.dp, end = 24.dp)
            .background(color = Gray, shape = RoundedCornerShape(8.dp)),
    ) {
        Row(
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 11.dp)
            )

            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = "${userName}님이 친구 질문응답을 요청했습니다.", fontSize = 12.sp)
                Text(
                    text = "12월 ${date}일",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        IconButton(
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
            onClick = { onItemSelected() },
        ) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
        }
    }
}

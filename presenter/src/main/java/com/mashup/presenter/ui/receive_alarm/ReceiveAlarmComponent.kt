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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.presenter.receive_alarm.model.RequestAlarmUiModel
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Grey_100
import com.mashup.presenter.ui.theme.Primary_2

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
            .background(color = Grey_100, shape = RoundedCornerShape(8.dp)),
    ) {
        val (requestAlarmInfoRef, moveRequestQuestionButtonRef) = createRefs()
        
        Row(
            modifier = Modifier.constrainAs(requestAlarmInfoRef) {
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
            modifier = Modifier.constrainAs(moveRequestQuestionButtonRef) {
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

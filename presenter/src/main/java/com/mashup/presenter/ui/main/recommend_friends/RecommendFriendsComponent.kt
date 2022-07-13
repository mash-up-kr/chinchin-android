package com.mashup.presenter.ui.main.recommend_friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.common.ChinChinCommonText
import com.mashup.presenter.ui.theme.Grey_500
import com.mashup.presenter.ui.theme.Grey_700
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1

@Composable
fun RecommendFriendsHeader(recommendFriendCount: Int) {
    Column(
        modifier = Modifier.padding(start = 24.dp)
    ) {
        /* TODO: Image 로 변경될 예정 */
        Text(
            text = "친친",
            fontSize = 30.sp,
            color = Primary_1,
            modifier = Modifier.padding(top = 6.dp, start = 4.dp)
        )

        ChinChinCommonText(
            text = "전체",
            highlightText = "$recommendFriendCount",
            modifier = Modifier.padding(top = 19.dp)
        )
    }
}

@Composable
fun RecommendFriendsPermissionBody() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "내 카톡 친구를 찾아보세요",
            color = Grey_700,
            fontSize = 20.sp
        )

        Text(
            text = "친구찾기 기능을 통해 친친에 가입한 친구들을\n" +
                "찾을 수 있습니다. 친구들에게 취향질문지를 보내고\n" +
                "친구에 대해 알아가보세요!",
            color = Grey_500,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.recommend_friends_permission),
            contentDescription = "",
            modifier = Modifier
                .width(195.dp)
                .height(156.dp)
        )

        RequestPermissionButton()
    }
}

@Composable
fun RequestPermissionButton(onButtonClick: () -> Unit = {}) {
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth()
            .padding(top = 25.dp, start = 24.dp, end = 24.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = "권한 동의하고 친구 찾기",
            fontSize = 16.sp,
            color = Grey_800,
        )
    }
}

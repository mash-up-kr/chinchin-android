package com.mashup.chinchin.presenter.ui.main.recommend_friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.main.model.RecommendFriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_700
import com.mashup.chinchin.presenter.ui.theme.Gray_800
import com.mashup.chinchin.presenter.ui.theme.Primary_1

@Composable
fun RecommendFriendsHeader(recommendFriendCount: Int) {
    Column {
        /* TODO: Image 로 변경될 예정 */
        Text(
            text = "친친",
            fontSize = 30.sp,
            color = Primary_1,
            modifier = Modifier.padding(top = 6.dp, start = 4.dp)
        )

        ChinChinText(
            text = "전체",
            highlightText = "$recommendFriendCount",
            modifier = Modifier.padding(top = 19.dp)
        )
    }
}

@Composable
fun RecommendFriendsPermissionBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 29.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "내 카톡 친구를 찾아보세요",
            color = Gray_700,
            fontSize = 20.sp
        )

        Text(
            text = "친구찾기 기능을 통해 친친에 가입한 친구들을\n" +
                "찾을 수 있습니다. 친구들에게 취향질문지를 보내고\n" +
                "친구에 대해 알아가보세요!",
            color = Gray_500,
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
            color = Gray_800,
        )
    }
}

@Composable
fun RecommendFriendsListBody(
    recommendFriendsList: List<RecommendFriendUiModel>,
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: RecommendFriendUiModel) -> Unit
) {
    LazyColumn {
        itemsIndexed(recommendFriendsList) { index, recommendFriend ->
            if (index == 0) {
                Divider(color = Color(0xFFD9D9D9), thickness = 0.5.dp)
            }
            RecommendFriendItem(recommendFriend, showBottomSheet, onSelectFriend)
            Divider(color = Color(0xFFD9D9D9), thickness = 0.5.dp)
        }
    }
}

@Composable
fun RecommendFriendItem(
    recommendFriend: RecommendFriendUiModel,
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: RecommendFriendUiModel) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        RecommendFriendInfo(recommendFriend, modifier = Modifier.padding(vertical = 8.dp))
        ChinChinButton(
            icon = R.drawable.icon_plus,
            buttonText = "친구 추가",
            modifier = Modifier
                .width(91.dp)
                .height(36.dp)
        ) {
            showBottomSheet.invoke()
            onSelectFriend.invoke(recommendFriend)
        }
    }
}

@Composable
fun RecommendFriendInfo(
    recommendFriend: RecommendFriendUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        AsyncImage(
            model = recommendFriend.profileUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
        )

        Text(
            text = recommendFriend.name,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

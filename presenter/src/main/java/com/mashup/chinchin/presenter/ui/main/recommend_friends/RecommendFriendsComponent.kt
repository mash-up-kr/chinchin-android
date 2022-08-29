package com.mashup.chinchin.presenter.ui.main.recommend_friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_700

@Composable
fun RecommendFriendsHeader(recommendFriendCount: Int) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.padding(top = 16.dp),
        )

        ChinChinText(
            text = "전체",
            highlightText = "$recommendFriendCount",
            modifier = Modifier.padding(top = 19.dp)
        )
    }
}

@Composable
fun RecommendFriendsPermissionBody(onButtonClick: () -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 29.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "내 카톡 친구를 찾아보세요",
                color = Gray_700,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "친친에 가입한 친구들을 찾을 수 있어요. \n" +
                        "친구들에게 취향질문지를 보내고\n" +
                        "친구에 대해 알아가보세요!",
                color = Gray_500,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.img_search),
                contentDescription = "",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            RequestPermissionButton(onButtonClick = onButtonClick)
        }
    }
}

@Composable
fun RequestPermissionButton(onButtonClick: () -> Unit = {}) {
    ChinChinConfirmButton(
        modifier = Modifier.padding(bottom = 20.dp),
        isEnable = true,
        buttonText = "권한 동의하고 친구 찾기"
    ) {
        onButtonClick()
    }
}

@Composable
fun RecommendFriendsListBody(
    recommendFriends: List<FriendUiModel>,
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: FriendUiModel) -> Unit,
    onClickMore: () -> Unit,
) {
    LazyColumn {
        itemsIndexed(recommendFriends) { index, recommendFriend ->
            if (index == 0) {
                Divider(color = Color(0xFFD9D9D9), thickness = 0.5.dp)
            }
            RecommendFriendItem(recommendFriend, showBottomSheet, onSelectFriend)
            Divider(color = Color(0xFFD9D9D9), thickness = 0.5.dp)
        }
        item {
            ChinChinConfirmButton(
                buttonText = "친구 더보기",
                isEnable = true,
            ) {
                onClickMore()
            }
        }
    }
}

@Composable
fun RecommendFriendsEmptyBody() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_findfriend),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun RecommendFriendItem(
    recommendFriend: FriendUiModel,
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: FriendUiModel) -> Unit,
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
    recommendFriend: FriendUiModel?,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        AsyncImage(
            model = recommendFriend?.profileUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape),
            error = painterResource(R.drawable.profile_default_image),
            placeholder = painterResource(R.drawable.profile_default_image),
        )

        Text(
            text = recommendFriend?.name ?: "",
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

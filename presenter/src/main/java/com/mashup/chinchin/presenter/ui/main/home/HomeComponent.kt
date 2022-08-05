package com.mashup.chinchin.presenter.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.*


@Composable
fun HomeBody(groups: List<FriendGroupUiModel> = listOf(), addGroup: () -> Unit = {}) {
    if (groups.isEmpty()) {
        EmptyFriendGroups {
            addGroup()
        }
    } else {
        FriendsGroupList(groups = groups)
    }
}

@Composable
fun EmptyFriendGroups(addGroup: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "텅 비었어요!",
            fontSize = 24.sp,
            color = Gray_500,
            modifier = Modifier.padding(vertical = 30.dp)
        )
        Image(painter = painterResource(id = R.drawable.empty_group), contentDescription = "empty groups")
        OutlinedButton(
            onClick = { addGroup() },
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = Gray_500
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp),
            border = BorderStroke(1.dp, Gray_500),
            modifier = Modifier
                .padding(top = 20.dp)
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
        ) {
            Text(
                text = "그룹 추가하기", modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(vertical = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        HomeHeader()
        HomeBody()
    }
}

@Composable
fun HomeHeader(onButtonClick: () -> Unit = {}) {
    Column {
        /* TODO: Image 로 변경될 예정 */
        Text(
            text = "친친",
            fontSize = 30.sp,
            color = Primary_1,
            modifier = Modifier.padding(top = 6.dp, start = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "친구를 추가하고\n취향을 수집해보세요!",
                    fontSize = 20.sp,
                    color = Black,
                    modifier = Modifier.padding(top = 18.dp)
                )

                Button(
                    onClick = { onButtonClick() },
                    shape = RoundedCornerShape(64.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
                    contentPadding = PaddingValues(horizontal = 30.dp, vertical = 20.dp),
                    modifier = Modifier
                        .defaultMinSize(1.dp, 1.dp)
                        .padding(top = 30.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                    ),
                ) {
                    Text(
                        text = "+ 친구 추가하기",
                        fontSize = 16.sp,
                        color = Gray_800,
                    )
                }
            }

            /* TODO: image 변경될 예정 */
            Image(
                painter = painterResource(id = R.drawable.image_124),
                contentDescription = "",
                modifier = Modifier
                    .size(126.dp)
                    .padding(end = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ChinChinText(
                text = "친친 그룹",
                highlightText = "${0}",
            )
            ChinChinButton(
                icon = R.drawable.icon_group_plus,
                buttonText = "그룹 추가"
            )
        }
    }
}

@Composable
fun FriendsGroupList(groups: List<FriendGroupUiModel>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(groups) { group ->
            FriendGroupCard(group)
        }
    }
}

@Composable
fun FriendGroupCard(friendGroup: FriendGroupUiModel) {
    val group = friendGroup

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Secondary_1,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            FriendGroupCardTitle(group.name)
            NumberOfFriends(group.friends.size)
            FriendProfileThumbnailList(group.friends)
        }
    }
}

@Composable
fun FriendGroupCardTitle(groupName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(start = 18.dp, top = 16.dp, end = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = groupName,
            fontWeight = FontWeight.Bold,
            color = Black,
            fontSize = 18.sp,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "GroupDetail",
            modifier = Modifier.padding(end = 9.dp)
        )
    }
}

@Composable
fun NumberOfFriends(friendsSize: Int) {
    Text(
        text = "${friendsSize}명",
        color = Gray_500,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 18.dp, top = 8.dp)
    )
}

@Composable
fun FriendProfileThumbnailList(friends: List<FriendUiModel>) {
    val profileMoreCount = if (friends.size > 5) {
        friends.size - 5
    } else {
        0
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        run loop@{
            friends.forEachIndexed { index, friend ->
                if (index < 5) {
                    FriendProfileThumbnail(thumbnailUrl = friend.profileThumbnailUrl)
                } else {
                    FriendProfileThumbnailMore(profileMoreCount)
                    return@forEachIndexed
                }
            }
        }
    }
}

@Composable
fun FriendProfileThumbnailMore(moreSize: Int) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .height(36.dp)
            .width(36.dp),
    ) {
        Text(
            text = "+${moreSize}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun FriendProfileThumbnail(thumbnailUrl: String) {
    AsyncImage(
        model = thumbnailUrl,
        contentDescription = "friendProfile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .border(2.dp, Secondary_1, CircleShape)
    )
}

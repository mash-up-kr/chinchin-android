package com.mashup.presenter.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.presenter.R
import com.mashup.presenter.main.model.Friend
import com.mashup.presenter.main.model.FriendGroupUiModel
import com.mashup.presenter.ui.common.ChinChinCommonButton
import com.mashup.presenter.ui.common.ChinChinCommonText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Grey_500
import com.mashup.presenter.ui.theme.Secondary_2

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeHeader()
}

@Composable
fun HomeHeader(onButtonClick: () -> Unit = {}) {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
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
                        color = Grey_800,
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
            ChinChinCommonText(
                text = "친친 그룹",
                highlightText = "${0}",
            )
            ChinChinCommonButton(
                icon = R.drawable.ic_add_group,
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
        modifier = Modifier
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Secondary_2,
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
        color = Grey_500,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 18.dp, top = 8.dp)
    )
}

@Composable
fun FriendProfileThumbnailList(friends: List<Friend>) {
    val more = if (friends.size > 5) {
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
                    FriendProfileThumbnailMore(more)
                    return@loop
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
            .border(2.dp, Secondary_2, CircleShape)
    )
}

@Composable
@Preview
fun PreviewFriendCardView() {
    val dummyGroup = FriendGroupUiModel(
        name = "매쉬업 사람들",
        friends = listOf(
            Friend("히지니", "https://picsum.photos/200"),
            Friend("혜찌니", "https://picsum.photos/200"),
            Friend("경무", "https://picsum.photos/200"),
            Friend("히지니", "https://picsum.photos/200"),
            Friend("혜찌니", "https://picsum.photos/200"),
            Friend("경무", "https://picsum.photos/200"),
            Friend("히지니", "https://picsum.photos/200"),
            Friend("혜찌니", "https://picsum.photos/200"),
            Friend("경무", "https://picsum.photos/200")
        )
    )

    val groups = mutableListOf<FriendGroupUiModel>()
    repeat(5) {
        groups.add(dummyGroup)
    }
    ChinchinTheme {
        FriendsGroupList(groups)
    }
}

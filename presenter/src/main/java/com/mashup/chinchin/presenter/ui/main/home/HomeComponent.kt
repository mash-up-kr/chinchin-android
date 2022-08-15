package com.mashup.chinchin.presenter.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinGrayTextField
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.*


@Composable
fun HomeBody(groups: List<FriendGroupUiModel> = listOf()) {
    if (groups.isEmpty()) {
        EmptyFriendGroups()
    } else {
        FriendsGroupList(groups = groups)
    }
}

@Composable
fun EmptyFriendGroups() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_group),
            contentDescription = "",
            modifier = Modifier.padding(top = 52.dp)
        )
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
fun HomeHeader(
    onButtonClick: () -> Unit = {},
    onBellClick: () -> Unit = {},
    onAddGroupClick: (Boolean) -> Unit = {},
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.padding(top = 17.dp),
            )

            IconButton(
                onClick = { onBellClick() },
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.icon_bell), contentDescription = "")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "친구를 추가하고\n취향을 수집해보세요!",
                    fontSize = 20.sp,
                    color = Black,
                    modifier = Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
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
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.img_giftbox),
                contentDescription = "",
                modifier = Modifier
                    .size(126.dp)
                    .padding(end = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ChinChinText(
                text = "친친 그룹",
                highlightText = "${0}",
            )
            ChinChinButton(
                icon = R.drawable.icon_group_plus,
                buttonText = "그룹 추가",
                onButtonClick = { onAddGroupClick(true) }
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
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddGroupDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit, addGroup: (String) -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = White,
                shape = RoundedCornerShape(8.dp)
            ) {
                AddGroupDialogContent(setShowDialog, addGroup)
            }
        }
    }
}

@Composable
fun AddGroupDialogContent(setShowDialog: (Boolean) -> Unit, addGroup: (String) -> Unit = {}) {
    var groupName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "새 그룹 추가하기",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 11.dp)
        )
        ChinChinGrayTextField(
            value = groupName,
            onValueChange = { groupName = it },
            placeHolder = "그룹명을 작성해주세요",
            paddingValues = PaddingValues(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 13.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    setShowDialog(false)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "취소하기",
                    fontWeight = FontWeight.Bold,
                    color = Gray_500,
                    fontSize = 16.sp
                )
            }
            Button(
                onClick = {
                    setShowDialog(false)
                    addGroup(groupName) // TODO: 그룹 추가하기~!!!!
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(
                    "추가하기",
                    fontWeight = FontWeight.Bold,
                    color = Primary_2,
                    fontSize = 16.sp
                )
            }
        }
    }
}

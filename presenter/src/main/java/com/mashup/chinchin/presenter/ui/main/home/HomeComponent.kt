package com.mashup.chinchin.presenter.ui.main.home

import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.group_detail.GroupDetailActivity
import com.mashup.chinchin.presenter.main.model.GroupInfoUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinButton
import com.mashup.chinchin.presenter.ui.common.ChinChinGrayTextField
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.*


@Composable
fun HomeBody(
    groups: List<GroupInfoUiModel> = listOf(),
) {
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
            modifier = Modifier.padding(top = 90.dp).size(200.dp)
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
    isAlarmExist: Boolean = false,
    onBellClick: () -> Unit = {},
    onAddGroupClick: (Boolean) -> Unit = {},
    groups: List<GroupInfoUiModel> = emptyList(),
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
                modifier = Modifier.padding(top = 16.dp),
            )

            Box {
                IconButton(
                    onClick = { onBellClick() },
                    modifier = Modifier
                        .padding(top = 5.dp, end = 5.dp)
                        .size(24.dp)
                        .align(Alignment.BottomStart),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_bell),
                        contentDescription = "",
                    )
                }
                if (isAlarmExist) {
                    Canvas(
                        modifier = Modifier
                            .size(9.dp)
                            .align(Alignment.TopEnd),
                        onDraw = {
                            drawCircle(color = Primary_2)
                        },
                    )
                }
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
                    .height(140.dp)
                    .width(120.dp)
                    .padding(end = 4.dp),
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
                highlightText = "${groups.size}",
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
fun FriendsGroupList(
    groups: List<GroupInfoUiModel>,
) {
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(groups) { group ->
            FriendGroupCard(
                groupInfo = group,
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, GroupDetailActivity::class.java).apply {
                        putExtra(GroupDetailActivity.FRIEND_GROUP_ID, group.groupId)
                    })
                }
            )
        }
    }
}

@Composable
fun FriendGroupCard(
    modifier: Modifier,
    groupInfo: GroupInfoUiModel,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Secondary_1,
        elevation = 0.dp,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            FriendGroupCardTitle(groupInfo.groupName)
            NumberOfFriends(groupInfo.groupMemberCount)
            FriendProfileThumbnailList(groupInfo.thumbnailImageUrls)
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
fun FriendProfileThumbnailList(thumbnailUrls: List<String>) {
    val profileMoreCount = if (thumbnailUrls.size > 5) {
        thumbnailUrls.size - 5
    } else {
        0
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        run loop@{
            thumbnailUrls.forEachIndexed { index, thumbnailUrl ->
                val start = (27 * index).dp
                if (index < 5) {
                    FriendProfileThumbnail(
                        thumbnailUrl = thumbnailUrl,
                        modifier = Modifier.padding(start = start),
                    )
                } else {
                    FriendProfileThumbnailMore(
                        moreSize = profileMoreCount,
                        Modifier.padding(start = start),
                    )
                    return@forEachIndexed
                }
            }
        }
    }
}

@Composable
fun FriendProfileThumbnailMore(moreSize: Int, modifier: Modifier = Modifier) {
    Card(
        shape = CircleShape,
        modifier = modifier
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
fun FriendProfileThumbnail(thumbnailUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = thumbnailUrl,
        contentDescription = "friendProfile",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .border(2.dp, Secondary_1, CircleShape)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddGroupDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    addGroup: (String) -> Unit
) {
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

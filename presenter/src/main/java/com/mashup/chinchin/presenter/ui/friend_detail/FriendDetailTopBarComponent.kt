package com.mashup.chinchin.presenter.ui.friend_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.chinchin.presenter.friend_detail.model.ProfileUiModel
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_800

@Preview(showBackground = true)
@Composable
fun FriendProfilePreview() {
    FriendProfile(
        profileUiModel = ProfileUiModel(
            name = "김매쉬",
            dateOfBirth = "12월 31일",
            thumbnailImageUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            groupName = "매쉬업 그룹",
            isMember = true,
            groupId = 1,
            id = 0
        )
    )
}

@Composable
fun FriendProfile(
    onProfileClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    profileUiModel: ProfileUiModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.clickable {
                onProfileClick()
            }
        ) {
            if (profileUiModel.isMember) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 11.dp)
                        .size(68.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.profile_default_image),
                    contentDescription = ""
                )
            } else {
                AsyncImage(
                    model = profileUiModel.thumbnailImageUrl,
                    contentDescription = "profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 11.dp)
                        .size(68.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.Center),
                    placeholder = painterResource(id = R.drawable.profile_default_image),
                    error = painterResource(R.drawable.profile_default_image),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.button_edit),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .height(34.dp)
            )

        }

        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = profileUiModel.name,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(top = 3.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = profileUiModel.dateOfBirth,
                fontSize = 12.sp,
                color = Gray_500,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Divider(
                color = Gray_500,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = profileUiModel.groupName,
                fontSize = 12.sp,
                color = Gray_500
            )
        }
        OutlinedButton(
            onClick = { onButtonClick() },
            shape = RoundedCornerShape(64.dp),
            border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(Gray_800)),
            contentPadding = PaddingValues(horizontal = 21.dp, vertical = 16.dp),
            modifier = Modifier
                .defaultMinSize(1.dp, 1.dp)
                .padding(top = 30.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
            ),
        ) {
            Text(
                text = "취향 질문 보내기",
                fontSize = 16.sp,
                color = Gray_800,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
    }
}



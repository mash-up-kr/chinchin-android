package com.mashup.presenter.ui.friend_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.presenter.R
import com.mashup.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.presenter.ui.common.ChinChinButton
import com.mashup.presenter.ui.theme.Grey_500

@Preview(showBackground = true)
@Composable
fun FriendProfilePreview() {
    FriendProfile(
        friendProfileUiModel = FriendProfileUiModel(
            "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
            "김매쉬",
            "12월 31일",
            "매쉬업 그룹"
        )
    )
}

@Composable
fun FriendProfile(friendProfileUiModel: FriendProfileUiModel) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = friendProfileUiModel.profileUrl,
            contentDescription = "profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
        )
        Column{
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                text = friendProfileUiModel.name,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Row(Modifier.padding(start = 18.dp, top = 2.dp)) {
                FriendProfileSubInfo(friendProfileUiModel.birthday, friendProfileUiModel.groupName)
                ChinChinButton(
                    icon = R.drawable.ic_edit,
                    buttonText = "프로필 편집",
                    modifier = Modifier
                        .padding(top = 10.dp, start = 32.dp)

                )
            }
        }
    }
}

@Composable
fun FriendProfileSubInfo(birthday: String, groupName: String) {
    Column {
        Text(
            text = birthday,
            fontSize = 14.sp,
            color = Grey_500,
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = groupName,
            fontSize = 14.sp,
            color = Grey_500
        )
    }
}




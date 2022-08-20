package com.mashup.chinchin.presenter.ui.group_detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.chinchin.presenter.friend_detail.FriendDetailActivity
import com.mashup.chinchin.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.Secondary_1

@Composable
fun GroupDetailList(context: Context, friends: List<FriendUiModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(friends) { friend ->
            GroupDetailItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .padding(horizontal = 24.dp),
                friend = friend,
                context = context,
            )
        }
    }
}

@Preview
@Composable
fun GroupDetailItemPreview() {
    GroupDetailItem(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .padding(horizontal = 24.dp),
        context = LocalContext.current,
        friend = FriendUiModel("김매쉬", ""),
    )
}

@Composable
fun GroupDetailItem(
    context: Context,
    modifier: Modifier = Modifier,
    friend: FriendUiModel
) {
    Card(
        modifier = modifier.clickable {
            val intent = Intent(context, FriendDetailActivity::class.java ).apply {
                // TODO: FriendId 넘기기
            }
            context.startActivity(intent)
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Secondary_1
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileThumbnail(friend.profileThumbnailUrl)
            ProfileName(friend.name)
        }

    }

}


@Composable
fun ProfileThumbnail(thumbnailUrl: String) {
    AsyncImage(
        model = thumbnailUrl,
        contentDescription = "profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape),
        )
}

@Composable
fun ProfileName(userName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            color = Black,
            fontSize = 16.sp,
        )
    }
}


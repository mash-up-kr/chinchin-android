package com.mashup.presenter.ui.group_detail

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mashup.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Primary_2
import com.mashup.presenter.ui.theme.Secondary_1


// TODO need to change to common composable
@Composable
fun TotalCountText(totalCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 0.dp)
    ) {
        Text(
            text = "전체",
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = "$totalCount",
            modifier = Modifier.padding(start = 8.dp),
            color = Primary_2,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun GroupDetailList(groupDetailUiModels: List<GroupDetailUiModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(groupDetailUiModels) { groupDetail ->
            GroupDetailItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .padding(horizontal = 24.dp),
                groupDetail = groupDetail
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
        groupDetail = GroupDetailUiModel("김매쉬", ""),
    )
}

@Composable
fun GroupDetailItem(
    modifier: Modifier = Modifier,
    groupDetail: GroupDetailUiModel
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Secondary_1
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileThumbnail(groupDetail.profileUrl)
            ProfileName(groupDetail.userName)
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


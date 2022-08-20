package com.mashup.chinchin.presenter.ui.group_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinFriendCard

@Composable
fun GroupDetailList(
    onClickCard: (FriendUiModel) -> Unit = {},
    friends: List<FriendUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(friends) { friend ->
            ChinChinFriendCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .padding(horizontal = 24.dp),
                friend = friend,
                onClickCard = { onClickCard(friend) }
            )
        }
    }
}

@Composable
fun EmptyGroupDetail() {
    Image(
        painter = painterResource(id = R.drawable.img_empty_addfriend),
        contentDescription = "",
    )
}

@Preview
@Composable
fun GroupDetailItemPreview() {
    ChinChinFriendCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp)
            .padding(horizontal = 24.dp),
        friend = FriendUiModel(0, "김매쉬", ""),
        onClickCard = {}
    )
}

package com.mashup.chinchin.presenter.ui.connect_friend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinFriendCard
import com.mashup.chinchin.presenter.ui.theme.Gray_100
import com.mashup.chinchin.presenter.ui.theme.Gray_400
import com.mashup.chinchin.presenter.ui.theme.Gray_800

@Composable
fun ConnectFriendSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String = "",
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(48.dp)
            .background(Gray_100),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = Gray_800
        ),
        cursorBrush = SolidColor(Gray_800),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeHolder,
                                color = Gray_400,
                                fontSize = 14.sp,
                            )
                        }
                        innerTextField()
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.icon_search),
                        contentDescription = ""
                    )
                }
            }
        },
    )
}

@Composable
fun TotalFriendList(
    friends: List<FriendUiModel>,
    modifier: Modifier = Modifier,
    onClickCard: (FriendUiModel) -> Unit = {}
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

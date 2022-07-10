package com.mashup.presenter.ui.group_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        //#F4F3EC
        items(groupDetailUiModels) { groupDetail ->
            GroupDetailItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .padding(start = 24.dp, end = 24.dp),
                userName = groupDetail.userName,
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
            .padding(start = 24.dp, end = 24.dp),
        userName = "혜진",
    )
}

@Composable
fun GroupDetailItem(
    modifier: Modifier = Modifier,
    userName: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        backgroundColor = Secondary_1
    ) {
        Row {
            Surface(
                modifier = Modifier.size(54.dp),
                shape = CircleShape
            )
            {
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "poster",
                )
            }

        }

    }

}
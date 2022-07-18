package com.mashup.presenter.ui.friend_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.common.ChinChinText
import com.mashup.presenter.ui.common.ChinChinYellowButton
import com.mashup.presenter.ui.theme.*


@Preview(showBackground = true)
@Composable
fun EmptyQuestionContentPreview() {
    EmptyQuestionContent()
}


@Composable
fun QuestionSizeText(size: Int) {
    ChinChinText(text = "총 질문", highlightText = "$size")
}

@Composable
fun EmptyQuestionContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "친구 대답을 확인할 수 없어요!",
            fontSize = 16.sp,
            color = Grey_600,
            modifier = Modifier.padding(top = 102.dp)
        )
        Text(
            text = "친구의 취향을 기록해 보세요",
            fontSize = 12.sp,
            color = Grey_400,
            modifier = Modifier.padding(top = 12.dp)
        )
        ChinChinYellowButton(
            text = "친구 취향 기록하기",
            fontSize = 16.sp
        ) {
            //Todo
            Log.i("hyejin", "EmptyQuestionContent: onClickButton")
        }
    }

}

@Preview(showBackground = true)
@Composable

fun TempSavedQuestionCardPreview() {
    TempSavedQuestionCard()
}

@Composable
fun TempSavedQuestionCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Grey_300,
        modifier = Modifier
            .padding(horizontal = 24.dp),

        ) {
        Column {
            Row {
                Text(
                    text = "임시 저장된 취향 질문이 있습니다.",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "tempSavedQuestionIcon",
                    modifier = Modifier.padding(start = 22.dp, end = 21.dp, top = 21.dp)
                )
            }

            Row {
                Text(
                    text = "작성하던 질문을 완성하고 친구에게 \n 보내보세요 :)",
                    fontSize = 12.sp,
                    color = Grey_500,
                    maxLines = 2,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.image_124),
                    contentDescription = "tempSavedQuestionImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(129.dp)
                        .height(89.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun QuestionItemPrivew() {
    QuestionItem()
}

@Composable
fun QuestionItem() {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Primary_1,
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = "")
                Text(
                    text = "좋아하는 음식은 무엇인가요?",
                    color = Grey_800,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )

            }

            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 0.dp,
                backgroundColor = White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "난 곱창이 세상에서 젤 좋아",
                    color = Grey_800,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

        }

    }
}
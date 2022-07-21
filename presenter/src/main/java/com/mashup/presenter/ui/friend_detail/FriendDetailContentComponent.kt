package com.mashup.presenter.ui.friend_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.friend_detail.FriendDetailActivity
import com.mashup.presenter.friend_detail.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinActingButton
import com.mashup.presenter.ui.common.ChinChinButton
import com.mashup.presenter.ui.common.ChinChinText
import com.mashup.presenter.ui.theme.*

@Preview(showBackground = true)
@Composable
fun QuestionSizeTextPreview() {
    QuestionSizeText(10)
}

@Composable
fun QuestionSizeText(size: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(63.dp)
            .padding(start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChinChinText(text = "총 질문", highlightText = "$size")
        ChinChinButton(icon = R.drawable.ic_group_plus, buttonText = "질문 추가")
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionAnswerListPreview() {
    QuestionAnswerListContent(FriendDetailActivity.initFriendAnswerList())
}

@Composable
fun QuestionAnswerListContent(
    answersFromFriend: List<QuestionUiModel>,
    modifier: Modifier = Modifier
) {
    Column {
        QuestionSizeText(answersFromFriend.size)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = modifier
        ) {
            itemsIndexed(answersFromFriend) { index, answer ->
                QuestionItem(index + 1, answer)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyQuestionContentPreview() {
    EmptyQuestionContent(false)
}

@Composable
fun EmptyQuestionContent(FROM_FRIEND: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = if (FROM_FRIEND) "친구 대답을 확인할 수 없어요!" else "아직 작성한 취향기록이 없어용!",
            fontSize = 16.sp,
            color = Gray_600,
            modifier = Modifier.padding(top = 102.dp)
        )
        Text(
            text = if (FROM_FRIEND) "친구의 취향을 기록해 보세요" else "친구를 초대하고 질문을 보내보세요",
            fontSize = 12.sp,
            color = Gray_400,
            modifier = Modifier.padding(top = 12.dp)
        )
        ChinChinActingButton(
            text = if (FROM_FRIEND) "친구 취향 기록하기" else "친구의 취향을 기록해보세요",
            fontSize = 16.sp,
            modifier = Modifier
                .defaultMinSize(1.dp, 1.dp)
                .padding(top = 39.dp),
        ) {
            // Todo OnclickListener
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TempSavedQuestionContentPreview() {
    TempSavedQuestionContent(10)
}

@Composable
fun TempSavedQuestionContent(size: Int) {
    Column {
        QuestionSizeText(size)
        TempSavedQuestionCard()
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
        backgroundColor = Gray_300,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "임시 저장된 취향 질문이 있습니다.",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = "tempSavedQuestionIcon",
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "작성하던 질문을 완성하고 친구에게 \n 보내보세요 :)",
                    fontSize = 12.sp,
                    color = Gray_500,
                )
                Image(
                    painter = painterResource(id = R.drawable.image_124),
                    contentDescription = "tempSavedQuestionImage",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(126.dp)
                        .height(89.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun QuestionItemPreview() {
    QuestionItem(
        1,
        QuestionUiModel(
            question = "좋아하는 음식은 무엇인가요?",
            answer = "난 곱창이 세상에서 젤 좋아"
        )
    )
}

@Composable
fun QuestionItem(index: Int, questionUiModel: QuestionUiModel) {
    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Primary_1,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberIcon(index)
                Text(
                    text = questionUiModel.question,
                    color = Gray_800,
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
                    text = questionUiModel.answer,
                    color = Gray_800,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun NumberIconPreview() {
    NumberIcon(1)
}

@Composable
fun NumberIcon(number: Int) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(20.dp), onDraw = {
            //TODO need to create Color Constant
            drawCircle(color = Color(0xFFF6C30D))
        })
        Text(
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Gray_700
        )

    }
}
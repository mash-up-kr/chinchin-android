package com.mashup.chinchin.presenter.ui.friend_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.ChinChinAnswerCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinAnswerCard
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.Gray_300
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.White

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
            .height(56.dp)
            .background(White)
            .padding(start = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChinChinText(text = "총 답변", highlightText = "$size")
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionAnswerListPreview() {
    QuestionAnswerListContent(
        cardState = ChinChinAnswerCardState.FRIEND_ANSWER
    )
}

@Composable
fun QuestionAnswerListContent(
    answers: List<QuestionUiModel> = emptyList(),
    cardState: ChinChinAnswerCardState
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .background(White),
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(top = 380.dp), // Todo 부모한테 측정된 값으로 넘겨 받도록 수정해야함
        ) {
            itemsIndexed(answers) { index, answers ->
                ChinChinAnswerCard(
                    index = index + 1,
                    question = answers.question,
                    answer = answers.answer,
                    cardState = cardState
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyQuestionContentPreview() {
    EmptyQuestionContent()
}

@Composable
fun EmptyQuestionContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 380.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_pencil), contentDescription = "",
            modifier = Modifier.size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TempSavedQuestionContentPreview() {
    TempSavedQuestionContent()
}

@Composable
fun TempSavedQuestionContent() {
    TempSavedQuestionCard()
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
            .padding(top = 380.dp)
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
                    painter = painterResource(id = R.drawable.img_giftbox),
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

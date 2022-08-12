package com.mashup.chinchin.presenter.ui.send_questions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_800
import com.mashup.chinchin.presenter.ui.theme.White

@Composable
fun SendPreferenceQuestionTitle(userName: String) {
    Column {
        Row {
            Text(
                text = userName,
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "에게 물어볼",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            Text(
                text = "키워드를 선택",
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "해주세요!",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun QuestionCategoryList(categories: List<String>) { // TODO: Category UiModel 생성 후 변경
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            QuestionCategoryChip(category = category)
        }
    }
}

@Composable
fun QuestionCategoryChip(category: String, onDialogOpen: (String) -> Unit = {}) {
    OutlinedButton(
        onClick = { onDialogOpen(category) },
        shape = RoundedCornerShape(16.dp),
        border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(Gray_500)),
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .wrapContentWidth()
            .height(32.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chip),
                contentDescription = "chip icons",
                tint = Gray_500
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = category,
                color = White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SendPreferenceQuestionList(
    questions: List<QuestionUiModel> = listOf()
) {
    // TODO: ChinChinQuestionCard로 구성하기
}

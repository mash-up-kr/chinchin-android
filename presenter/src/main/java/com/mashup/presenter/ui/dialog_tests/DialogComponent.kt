package com.mashup.presenter.ui.dialog_tests

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mashup.presenter.R
import com.mashup.presenter.ui.common.ChinChinConfirmButton
import com.mashup.presenter.ui.theme.Gray_300
import com.mashup.presenter.ui.theme.Primary_1
import com.mashup.presenter.ui.theme.White

@Preview(showBackground = true)
@Composable
fun QuestionCategoryDialogPreview() {
    QuestionCategoryDialog(
        category = "취향 키워드",
        keywords = listOf(
            "싫어하는 향", "좋아하는 음식", "좋아하는 향", "좋아하는 옷 브랜드",
            "좋아하는 꽃", "좋아하는 술"
        ),
        onClick = {},
        onDismissRequest = {},
    )

}

@Composable
fun QuestionCategoryDialog(
    category: String,
    selectedKeyword: String = "",
    keywords: List<String>,
    onClick: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            modifier = Modifier
                .width(310.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            color = White,
        ) {
            QuestionCategoryDialogContent(
                category = category,
                selectedKeyword = selectedKeyword,
                keywords = keywords,
                onDismissRequest = onDismissRequest,
                onClick = onClick
            )
        }
    }
}

@Composable
fun QuestionCategoryDialogContent(
    category: String,
    selectedKeyword: String = "",
    keywords: List<String>,
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit,
) {
    val curSelectedKeyword: MutableState<String> = remember {
        mutableStateOf(selectedKeyword)
    }

    val onChangedState: (String) -> Unit = { curSelectedKeyword.value = it }
    val isEnable: () -> Boolean = { curSelectedKeyword.value != "" }
    val isCurSelectedItem: (String) -> Boolean = { curSelectedKeyword.value == it }

    Column {
        QuestionCategoryTitle(text = category, onDismissRequest = onDismissRequest)
        Divider(
            color = Gray_300,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        QuestionCategoryRadioButtons(
            isCurSelectedItem = isCurSelectedItem,
            onChangeState = onChangedState,
            keywords = keywords,
        )
        Divider(
            color = Gray_300,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 71.dp)
                .padding(top = 16.dp, bottom = 24.dp)
        ) {

            ChinChinConfirmButton(
                buttonText = "키워드 선택하기",
                radius = 64.dp,
                isEnable = isEnable(),
            ) {
                if (isEnable()) {
                    onClick(curSelectedKeyword.value)
                    onDismissRequest()
                }
            }
        }

    }

}

@Composable
fun QuestionCategoryTitle(
    text: String,
    onDismissRequest: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
        )
        IconButton(onClick = { onDismissRequest() }, modifier = Modifier.size(24.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_x),
                contentDescription = "quit dialog"
            )
        }
    }
}

@Composable
fun QuestionCategoryRadioButtons(
    keywords: List<String>,
    isCurSelectedItem: (String) -> Boolean,
    onChangeState: (String) -> Unit,
) {
    Column {
        keywords.forEach { keyword ->
            QuestionCategoryRadioButton(keyword, isCurSelectedItem, onChangeState)
        }
    }
}

@Composable
fun QuestionCategoryRadioButton(
    keyword: String,
    isCurSelectedItem: (String) -> Boolean,
    onChangeState: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .selectable(
                selected = isCurSelectedItem(keyword),
                onClick = { onChangeState(keyword) },
                role = Role.RadioButton
            )
            .padding(horizontal = 32.dp, vertical = 18.dp)
    ) {
        RadioButton(
            selected = isCurSelectedItem(keyword),
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary_1,
                unselectedColor = Gray_300,
                disabledColor = Gray_300
            )
        )
        Text(
            text = keyword,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp), fontSize = 16.sp
        )
    }
}

package com.mashup.chinchin.presenter.dialog_test

import QuestionCategoryDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme

//TODO : need to delete activity
class DialogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val selectedKeyword: MutableState<String> = remember {
                mutableStateOf("")
            }
            val onClick: (String) -> Unit = { selectedKeyword.value = it }
            val keywords = listOf(
                "싫어하는 향", "좋아하는 음식", "좋아하는 향", "좋아하는 옷 브랜드",
                "좋아하는 꽃", "좋아하는 술"
            )
            ChinchinTheme {
                DialogScreen(selectedKeyword.value, keywords, onClick)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogScreenPreview() {
    DialogScreen {}
}

@Composable
fun DialogScreen(
    selectedKeyword: String = "",
    keywords: List<String> = emptyList(),
    onClick: (String) -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        QuestionCategoryDialog(
            category = "취향 키워드",
            selectedKeyword = selectedKeyword,
            keywords = keywords,
            onClick = onClick
        ) {
            showDialog.value = false
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showDialog.value = true
        }) {
            Text(text = "취향 키워드")
            Text(text = selectedKeyword)
        }
    }
}

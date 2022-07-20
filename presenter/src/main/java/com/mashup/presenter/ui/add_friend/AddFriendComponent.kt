package com.mashup.presenter.ui.add_friend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.common.ChinChinTitleAndTextFieldButton
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Gray_100
import com.mashup.presenter.ui.theme.Gray_400
import com.mashup.presenter.ui.theme.Gray_500
import com.mashup.presenter.ui.theme.Gray_800

@Composable
fun AddFriendTitles() {
    Column {
        Text(
            text = "친구의 인적사항 및 그룹을 지정해주세요",
            fontSize = 18.sp,
            color = Gray_800,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "아래 항목들은 언제든지 수정가능합니다.",
            fontSize = 12.sp,
            color = Gray_500,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun AddFriendContents(
    friendName: String, onValueChanged: (String) -> Unit,
) {
    Column {
        AddFriendNameComponent(friendName = friendName, onValueChanged = onValueChanged)
        Spacer(modifier = Modifier.height(16.dp))
        ChinChinTitleAndTextFieldButton(title = "생일 *", iconRes = R.drawable.icon_calendar)
        Spacer(modifier = Modifier.height(16.dp))
        ChinChinTitleAndTextFieldButton(title = "친친 그룹 *", iconRes = R.drawable.icon_arrow, placeHolder = "그룹 지정하기")
    }
}

@Composable
fun AddFriendNameComponent(friendName: String, onValueChanged: (String) -> Unit) {
    Column {
        Text(
            text = "이름 *",
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
        )
        TextField(
            value = friendName,
            onValueChange = { onValueChanged(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Gray_100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(48.dp),
            placeholder = { Text(text = "친구 이름", color = Gray_400) },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp
            )
        )
    }
}

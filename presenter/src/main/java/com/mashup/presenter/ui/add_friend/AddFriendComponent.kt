package com.mashup.presenter.ui.add_friend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Grey_100
import com.mashup.presenter.ui.theme.Grey_400
import com.mashup.presenter.ui.theme.Grey_500
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1

@Composable
fun AddFriendTitles() {
    Column {
        Text(
            text = "친구의 인적사항 및 그룹을 지정해주세요",
            fontSize = 18.sp,
            color = Grey_800,
        )
        Text(
            text = "아래 항목들은 언제든지 수정가능합니다.",
            fontSize = 12.sp,
            color = Grey_500,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun AddFriendContents(
    friendName: String, onValueChanged: (String) -> Unit
) {
    Column {
        AddFriendNameComponent(friendName = friendName, onValueChanged = onValueChanged)
        Spacer(modifier = Modifier.height(16.dp))
        AddFriendBirthDayComponent()
        Spacer(modifier = Modifier.height(16.dp))
        AddFriendGroupComponent()
    }
}

@Composable
fun AddFriendNameComponent(friendName: String, onValueChanged: (String) -> Unit) {
    Column {
        Text(text = "이름 *", fontSize = 16.sp, color = Black)
        TextField(
            value = friendName,
            onValueChange = { onValueChanged(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Grey_100,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(48.dp),
            placeholder = { Text(text = "친구 이름", color = Grey_400) },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp
            )
        )
    }
}

@Composable
fun AddFriendBirthDayComponent(onButtonClick: () -> Unit = {}) {
    Column {
        Text(text = "생일 *", fontSize = 16.sp, color = Black)
        Button(
            onClick = { onButtonClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Grey_100),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(48.dp)
                .defaultMinSize(1.dp, 1.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
            ),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(id = R.drawable.icon_calendar),
                    "",
                    tint = Grey_500
                )
            }
        }
    }
}

@Composable
fun AddFriendGroupComponent(onButtonClick: () -> Unit = {}) {
    Column {
        Text(text = "친친 그룹 *", fontSize = 16.sp, color = Black)
        Button(
            onClick = { onButtonClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Grey_100),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
                .height(48.dp)
                .defaultMinSize(1.dp, 1.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
            ),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "그룹 지정하기",
                    color = Grey_400,
                    fontSize = 14.sp
                )
                Icon(
                    painterResource(id = R.drawable.icon_arrow),
                    "",
                    tint = Grey_500
                )
            }
        }
    }
}

@Composable
fun AddFriendEnableConfirmButton(onButtonClick: () -> Unit) {
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
        contentPadding = PaddingValues(vertical = 20.dp),
        modifier = Modifier
            .defaultMinSize(minHeight = 1.dp)
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = "친구 취향 기록하기",
            fontSize = 16.sp,
            color = Grey_800,
        )
    }
}

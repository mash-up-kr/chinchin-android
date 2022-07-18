package com.mashup.presenter.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1
import org.w3c.dom.Text


@Composable
fun ChinChinYellowButton(text: String, fontSize: TextUnit,  onButtonClick: () -> Unit = {}){
    Button(
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(64.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Primary_1),
        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 20.dp),
        modifier = Modifier
            .defaultMinSize(1.dp, 1.dp)
            .padding(top = 30.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = "친구 취향 기록하기",
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Grey_800,
        )
    }
}
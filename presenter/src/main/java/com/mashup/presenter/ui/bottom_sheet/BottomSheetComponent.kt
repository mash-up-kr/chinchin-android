package com.mashup.presenter.ui.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Gray_200
import com.mashup.presenter.ui.theme.Gray_400
import com.mashup.presenter.ui.theme.White

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetContent()
}

@Composable
fun BottomSheetContent() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = White,
    ) {
        Column {
            BottomSheetTitle(title = "다음 단계를 선택해 주세요")
            Divider(
                color = Gray_200,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Column(modifier = Modifier.padding(start = 32.dp)) {
                BottomSheetTextItem(text = "저장하기", iconResId = R.drawable.ic_save)
                BottomSheetTextItem(text = "친구에게 질문 보내기", iconResId = R.drawable.ic_send)
                BottomSheetTextItem(text = "취소", iconResId = R.drawable.ic_x)
            }

        }
    }
}

@Composable
fun BottomSheetTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        modifier = Modifier
            .padding(start = 24.dp)
            .padding(vertical = 16.dp)
    )
}

@Composable
fun BottomSheetTextItem(text: String, iconResId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = text,
            tint = Gray_400,
        )

        Text(
            text = text,
            fontSize = 16.sp,
            color = if (text == "취소") Gray_400 else Black,
            modifier = Modifier.padding(start = 18.dp)
        )
    }

}


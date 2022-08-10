package com.mashup.presenter.ui.common.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.Gray_200
import com.mashup.chinchin.presenter.ui.theme.Gray_400
import com.mashup.chinchin.presenter.ui.theme.White
import com.mashup.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    BottomSheetContent(
        "다음 단계를 선택해 주세요", listOf(
            BottomSheetItemUiModel("저장하기", R.drawable.icon_user_more1, onClick = {}),
            BottomSheetItemUiModel("친구에게 질문 보내기", R.drawable.icon_connect, onClick = {}),
            BottomSheetItemUiModel("취소", R.drawable.ic_x, onClick = {}),
        )
    )
}

@Composable
fun BottomSheetContent(title: String, items: List<BottomSheetItemUiModel>) {
    Column {
        BottomSheetTitle(title = title)
        Divider(
            color = Gray_200,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        items.forEach { item ->
            BottomSheetTextItem(text = item.text, iconResId = item.iconResId) {
                item.onClick()
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
fun BottomSheetTextItem(text: String, iconResId: Int, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        modifier = Modifier.defaultMinSize(1.dp, 1.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = iconResId),
                contentDescription = text,
                tint = Gray_400,
            )

            Text(
                text = text,
                fontSize = 16.sp,
                color = Black,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }

}


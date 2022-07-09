package com.mashup.presenter.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Grey_400
import com.mashup.presenter.ui.theme.Grey_800
import com.mashup.presenter.ui.theme.Primary_1
import com.mashup.presenter.ui.theme.Primary_2

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeHeader()
}

@Composable
fun HomeHeader(onButtonClick: () -> Unit = {}) {
    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        /* TODO: Image 로 변경될 예정 */
        Text(
            text = "친친",
            fontSize = 30.sp,
            color = Primary_1,
            modifier = Modifier.padding(top = 6.dp, start = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "친구를 추가하고\n취향을 수집해보세요!",
                    fontSize = 20.sp,
                    color = Black,
                    modifier = Modifier.padding(top = 18.dp)
                )

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
                        text = "+ 친구 추가하기",
                        fontSize = 16.sp,
                        color = Grey_800,
                    )
                }
            }

            /* TODO: image 변경될 예정 */
            Image(
                painter = painterResource(id = R.drawable.image_124),
                contentDescription = "",
                modifier = Modifier.size(126.dp).padding(end = 4.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 26.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleWithCountText(
                title = "친친 그룹",
                countText = "${0}",
            )
            ChinChinCommonButton(
                icon = R.drawable.ic_add_group,
                buttonText = "그룹 추가"
            )
        }
    }
}

/* TODO: 이 아래는 공통으로 분리한다. */
@Composable
fun TitleWithCountText(title: String, countText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = Black,
            fontSize = 18.sp
        )
        Text(
            text = countText,
            modifier = Modifier.padding(start = 8.dp),
            color = Primary_2,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun ChinChinCommonButton(
    icon: Int,
    buttonText: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
) {
    OutlinedButton(
        onClick = { onButtonClick() },
        modifier = modifier.defaultMinSize(1.dp, 1.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 11.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = Grey_400
            )
            Text(
                text = buttonText,
                color = Grey_400,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

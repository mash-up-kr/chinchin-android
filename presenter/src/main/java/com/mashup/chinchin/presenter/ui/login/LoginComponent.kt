package com.mashup.chinchin.presenter.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.ui.theme.Gray_800
import com.mashup.chinchin.presenter.ui.theme.KakaoYellow

@Composable
fun IntroductionText() {
    val guide = "질문으로 서로를 알아가는\n친구 취향수집 서비스"
    Text(
        text = guide,
        color = Gray_800,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 20.dp)
    )
}

@Composable
fun IntroductionImage() {
    Image(
        painter = painterResource(id = R.drawable.img_aduck),
        contentDescription = "회원가입 이미지",
    )
}

@Composable
fun KakaoLoginButton(kakaoLogin: () -> Unit) {
    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = { kakaoLogin() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = KakaoYellow
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_kakao), contentDescription = "")
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "카카오톡으로 시작하기",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 16.sp
        )
    }
}

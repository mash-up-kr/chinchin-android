package com.mashup.presenter.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakao.sdk.user.UserApiClient
import com.mashup.presenter.R
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        setContent {
            ChinchinTheme {
                LoginPage {
                    kakaoLogin()
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(this) {
            Log.e("LoginActivity 로그인할때 에러났대요", it)
        }
    }

    private fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                viewModel.handleKakaoCallback(token, error)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                viewModel.handleKakaoCallback(token, error)
            }
        }
    }
}

@Composable
private fun IntroductionText() {
    val guide = "질문으로 서로를 알아가는 \n친구 취향수집 서비스, 친친!"
    Text(
        text = guide,
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding()
    )
}

@Composable
private fun IntroductionImage() {
    Image(painter = painterResource(id = R.drawable.signup), contentDescription = "회원가입 이미지")
}

@Composable
private fun KakaoLoginButton(kakaoLogin: () -> Unit) {
    Button(
        shape = RoundedCornerShape(10.dp),
        onClick = { kakaoLogin() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.kakao_yellow)
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

@Composable
private fun LoginPage(kakaoLogin: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(vertical = 80.dp)
    ) {
        IntroductionText()
        Spacer(modifier = Modifier.height(30.dp))
        IntroductionImage()
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 32.dp)
    ) {
        KakaoLoginButton {
            kakaoLogin()
        }
    }
}

@Preview
@Composable
fun LoginPagePreview() {
    ChinchinTheme {
        LoginPage {
            null
        }
    }
}
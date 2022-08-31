package com.mashup.chinchin.presenter.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kakao.sdk.user.UserApiClient
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.main.MainActivity
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.login.IntroductionImage
import com.mashup.chinchin.presenter.ui.login.IntroductionText
import com.mashup.chinchin.presenter.ui.login.KakaoLoginButton
import com.mashup.chinchin.presenter.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        setContent {
            ChinchinTheme {
                LoginScreen {
                    kakaoLogin()
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(this) {
            Log.e("LoginActivity 로그인할때 에러났대요", it)
        }
        viewModel.isLoginSuccess.observe(this) {
            onChinChinLoginEvent(it)
        }
    }

    private fun onChinChinLoginEvent(isSuccess: Boolean) {
        if (isSuccess) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "로그인이 실패했습니다.", Toast.LENGTH_SHORT).show()
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
private fun LoginScreen(kakaoLogin: () -> Unit = {}) {
    StatusBarColor()
    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .width(149.dp)
                    .height(83.dp)
                    .padding(bottom = 10.dp)
            )
            IntroductionText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 43.dp)
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IntroductionImage(
                modifier = Modifier.fillMaxWidth()
            )
            KakaoLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 80.dp)
                    .align(alignment = Alignment.BottomCenter)
            ) {
                kakaoLogin()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    ChinchinTheme {
        LoginScreen()
    }
}

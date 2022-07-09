package com.mashup.presenter.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kakao.sdk.user.UserApiClient
import com.mashup.presenter.ui.IntroductionImage
import com.mashup.presenter.ui.IntroductionText
import com.mashup.presenter.ui.KakaoLoginButton
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
        LoginScreen()
    }
}
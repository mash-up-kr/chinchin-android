package com.mashup.presenter.dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mashup.presenter.add_friend.AddFriendActivity
import com.mashup.presenter.dialog_test.DialogActivity
import com.mashup.presenter.friend_detail.FriendDetailActivity
import com.mashup.presenter.group_detail.GroupDetailActivity
import com.mashup.presenter.login.LoginActivity
import com.mashup.presenter.main.MainActivity
import com.mashup.presenter.receive_alarm.ReceiveAlarmActivity
import com.mashup.presenter.reply_preference.ReplyPreferenceActivity
import com.mashup.presenter.send_preference.SendPreferenceActivity
import com.mashup.presenter.send_preference.SendPreferenceCompleteActivity
import com.mashup.presenter.set_group.SetGroupActivity
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 개발용 페이지입니다.
 * 개발 완료되면 사라질 예정
 * */
@AndroidEntryPoint
class BridgeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BridgeList(
                        items = arrayListOf(
                            MainActivity::class.java,
                            BridgeActivity::class.java,
                            LoginActivity::class.java,
                            ReceiveAlarmActivity::class.java,
                            GroupDetailActivity::class.java,
                            FriendDetailActivity::class.java,
                            SetGroupActivity::class.java,
                            AddFriendActivity::class.java,
                            ReplyPreferenceActivity::class.java,
                            DialogActivity::class.java,
                            SendPreferenceActivity::class.java,
                            SendPreferenceCompleteActivity::class.java,
                        )
                    )
                }
            }
        }
    }
}

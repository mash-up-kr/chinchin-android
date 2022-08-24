package com.mashup.chinchin.presenter.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity.Companion.NEW_FRIEND
import com.mashup.chinchin.presenter.connect_friend.ConnectFriendActivity
import com.mashup.chinchin.presenter.connect_friend.ConnectFriendActivity.Companion.OLD_FRIEND
import com.mashup.chinchin.presenter.main.home.HomeViewModel
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.receive_alarm.ReceiveAlarmActivity
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.main.MainNavBar
import com.mashup.chinchin.presenter.ui.main.MainNavGraph
import com.mashup.chinchin.presenter.ui.main.MainNavScreen
import com.mashup.chinchin.presenter.ui.main.home.AddGroupDialog
import com.mashup.chinchin.presenter.ui.main.home.HomeBody
import com.mashup.chinchin.presenter.ui.main.home.HomeHeader
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsEmptyBody
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsHeader
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsListBody
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsPermissionBody
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Gray_300
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(
                        recommendFriends = initRecommendFriends(),
                    )
                }
            }
        }
    }

    private fun initRecommendFriends(): List<FriendUiModel> {
        return List(25) {
            FriendUiModel(0, "안경무 $it", "https://picsum.photos/200")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    screens: List<MainNavScreen> = MainNavScreen.values().toList(),
    recommendFriends: List<FriendUiModel> = listOf(),
    groups: List<FriendGroupUiModel> = listOf(),
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    val selectedFriend = remember { mutableStateOf(FriendUiModel(0, "", "")) }
    val onSelectFriend: (friend: FriendUiModel) -> Unit = { friend ->
        selectedFriend.value = friend
    }
    val coroutineScope = rememberCoroutineScope()

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val showBottomSheet: () -> Unit = {
        coroutineScope.launch {
            modalBottomSheetState.show()
        }
    }

    val closeBottomSheet: () -> Unit = {
        coroutineScope.launch {
            modalBottomSheetState.hide()
        }
    }

    if (currentDestination.route == MainNavScreen.RecommendFriends.route) {
        BackHandler(enabled = modalBottomSheetState.isVisible) {
            closeBottomSheet()
        }
    }

    StatusBarColor()

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            BottomSheetContent(
                "친구 추가할까요?", listOf(
                    BottomSheetItemUiModel("신규 친구 추가하기", R.drawable.icon_user_more1) {
                        context.startActivity(Intent(context, AddFriendActivity::class.java)
                            .putExtra(NEW_FRIEND, selectedFriend.value)
                        )
                    },
                    BottomSheetItemUiModel("기존 친구에 연결하기", R.drawable.icon_connect) {
                        context.startActivity(Intent(context, ConnectFriendActivity::class.java).apply {
                            putExtra(OLD_FRIEND, selectedFriend.value)
                        })
                    },
                    BottomSheetItemUiModel("취소", R.drawable.ic_x) {
                        closeBottomSheet()
                    },
                )
            )
        },
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    )
    {
        Scaffold(
            bottomBar = {
                MainNavBar(screens = screens, currentDestination = currentDestination) { screen ->
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
        ) { paddingValues ->
            MainNavGraph(
                navController = navController,
                recommendFriends = recommendFriends,
                showBottomSheet,
                onSelectFriend,
                bottomPaddingValue = paddingValues.calculateBottomPadding()
            )
        }
    }
}

@Composable
fun HomeScreen(bottomPaddingValue: Dp = 0.dp) {
    val viewModel: HomeViewModel = hiltViewModel()
    viewModel.getGroups()

    val context = LocalContext.current
    val groups = viewModel.groups.observeAsState().value ?: FriendGroupUiModel(emptyList())
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = bottomPaddingValue)) {
        HomeHeader(
            onButtonClick = {
                context.startActivity(
                    Intent(
                        context,
                        AddFriendActivity::class.java
                    )
                )
            },
            onAddGroupClick = setShowDialog,
            onBellClick = {
                context.startActivity(
                    Intent(
                        context,
                        ReceiveAlarmActivity::class.java
                    )
                )
            },
            groups = groups.groups
        )
        HomeBody(groups = groups.groups)
        AddGroupDialog(
            showDialog = showDialog,
            setShowDialog = setShowDialog,
            addGroup = { groupName ->
                viewModel.createNewGroup(groupName)
            }
        )
    }
}

@Composable
fun RecommendFriendsScreen(
    recommendFriendsList: List<FriendUiModel> = listOf(),
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: FriendUiModel) -> Unit,
    bottomPaddingValue: Dp = 0.dp,
    onClickMore: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 20.dp)
            .padding(bottom = bottomPaddingValue)
    ) {
        RecommendFriendsHeader(recommendFriendsList.size)
        Spacer(modifier = Modifier.height(7.dp))

        /* TODO: 퍼미션 체크를 기준으로 변경하자 */
        val isAgreedKakaoPermission = false

        if (isAgreedKakaoPermission) {
            RecommendFriendsPermissionBody()
        } else {
            if (recommendFriendsList.isEmpty()) {
                RecommendFriendsEmptyBody()
            } else {
                RecommendFriendsListBody(
                    recommendFriendsList = recommendFriendsList,
                    showBottomSheet = showBottomSheet,
                    onSelectFriend = onSelectFriend,
                    onClickMore = onClickMore,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen()
}

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        MoreItemButton(text = "이용 안내", onClickButton = { })
        MoreItemButton(text = "만든이들", onClickButton = { })
        MoreItemButton(text = "피드백 보내기", onClickButton = { })
    }
}

@Composable
fun MoreItemButton(
    text: String,
    onClickButton: () -> Unit,
) {
    Button(
        onClick = { onClickButton() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
        modifier = Modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        contentPadding = PaddingValues(vertical = 20.dp),
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Black,
            fontWeight = FontWeight.Bold,
            text = text,
        )

    }
    Divider(
        color = Gray_300,
        thickness = 0.5.dp,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
@Preview
fun PreviewMain() {
    ChinchinTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen(
                listOf(
                    MainNavScreen.Home,
                    MainNavScreen.RecommendFriends,
                    MainNavScreen.More,
                )
            )
        }
    }
}

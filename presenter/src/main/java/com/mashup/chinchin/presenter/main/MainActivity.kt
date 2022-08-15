package com.mashup.chinchin.presenter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.main.model.FriendUiModel
import com.mashup.chinchin.presenter.main.model.RecommendFriendUiModel
import com.mashup.chinchin.presenter.ui.main.MainNavBar
import com.mashup.chinchin.presenter.ui.main.MainNavGraph
import com.mashup.chinchin.presenter.ui.main.MainNavScreen
import com.mashup.chinchin.presenter.ui.main.home.HomeBody
import com.mashup.chinchin.presenter.ui.main.home.HomeHeader
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsHeader
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsListBody
import com.mashup.chinchin.presenter.ui.main.recommend_friends.RecommendFriendsPermissionBody
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
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
                        recommendFriends = initRecommendFriends()
                    )
                }
            }
        }
    }

    private fun initRecommendFriends(): List<RecommendFriendUiModel> {
        return List(25) {
            RecommendFriendUiModel("good", "안경무 $it")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    screens: List<MainNavScreen> =  MainNavScreen.values().toList(),
    recommendFriends: List<RecommendFriendUiModel> = listOf()
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    val selectedFriend = remember { mutableStateOf(RecommendFriendUiModel("", "")) }
    val onSelectFriend: (friend: RecommendFriendUiModel) -> Unit = { friend ->
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

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            BottomSheetContent(
                "친구 추가할까요?", listOf(
                    BottomSheetItemUiModel("신규 친구 추가하기", R.drawable.icon_user_more1) {
                        closeBottomSheet() //TODO 신규 추가하기 로직 구현 해야함
                    },
                    BottomSheetItemUiModel("기존 친구에 연결하기", R.drawable.icon_connect) {},//TODO
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
        ) {
            MainNavGraph(
                navController = navController,
                recommendFriends = recommendFriends,
                showBottomSheet,
                onSelectFriend,
            )
        }
    }
}

@Composable
fun HomeScreen() {
    val groups = mutableListOf<FriendGroupUiModel>()

    repeat(20) {
        val dummyGroup = FriendGroupUiModel(
            name = "매쉬업 사람들",
            friends = listOf(
                FriendUiModel("히지니", "https://picsum.photos/200"),
                FriendUiModel("혜찌니", "https://picsum.photos/200"),
                FriendUiModel("경무", "https://picsum.photos/200"),
                FriendUiModel("히지니", "https://picsum.photos/200"),
                FriendUiModel("혜찌니", "https://picsum.photos/200"),
                FriendUiModel("경무", "https://picsum.photos/200")
            )
        )
        groups.add(dummyGroup)
    }

    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp)) {
        HomeHeader()
        HomeBody(groups)
    }
}

@Composable
fun RecommendFriendsScreen(
    recommendFriendsList: List<RecommendFriendUiModel> = listOf(),
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: RecommendFriendUiModel) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        RecommendFriendsHeader(recommendFriendsList.size)
        Spacer(modifier = Modifier.height(7.dp))

        /* TODO: 퍼미션 체크를 기준으로 변경하자 */
        if (recommendFriendsList.isEmpty()) {
            RecommendFriendsPermissionBody()
        } else {
            RecommendFriendsListBody(recommendFriendsList, showBottomSheet, onSelectFriend)
        }
    }
}

@Composable
fun MoreScreen() {
    ConstraintLayout(modifier = Modifier.background(Color.Green)) {

        val text = createRef()
        Text("More", Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
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

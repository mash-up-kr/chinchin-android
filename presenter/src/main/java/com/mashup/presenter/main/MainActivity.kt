package com.mashup.presenter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.presenter.main.model.RecommendFriendUiModel
import com.mashup.presenter.main.model.FriendGroupUiModel
import com.mashup.presenter.main.model.FriendUiModel
import com.mashup.presenter.ui.main.MainNavBar
import com.mashup.presenter.ui.main.MainNavGraph
import com.mashup.presenter.ui.main.MainNavScreen
import com.mashup.presenter.ui.main.home.FriendsGroupList
import com.mashup.presenter.ui.main.home.HomeHeader
import com.mashup.presenter.ui.main.recommend_friends.RecommendFriendsListBody
import com.mashup.presenter.ui.main.recommend_friends.RecommendFriendsHeader
import com.mashup.presenter.ui.main.recommend_friends.RecommendFriendsPermissionBody
import com.mashup.presenter.ui.main.home.HomeBody
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

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
            RecommendFriendUiModel("good", "????????? $it")
        }
    }
}

@Composable
fun MainScreen(
    screens: List<MainNavScreen> =  MainNavScreen.values().toList(),
    recommendFriends: List<RecommendFriendUiModel> = listOf()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainNavScreen.fromRoute(navBackStackEntry?.destination?.route)

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
        )
    }
}

@Composable
fun HomeScreen() {
    val groups = mutableListOf<FriendGroupUiModel>()

    repeat(20) {
        val dummyGroup = FriendGroupUiModel(
            name = "????????? ?????????",
            friends = listOf(
                FriendUiModel("?????????", "https://picsum.photos/200"),
                FriendUiModel("?????????", "https://picsum.photos/200"),
                FriendUiModel("??????", "https://picsum.photos/200"),
                FriendUiModel("?????????", "https://picsum.photos/200"),
                FriendUiModel("?????????", "https://picsum.photos/200"),
                FriendUiModel("??????", "https://picsum.photos/200")
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
fun RecommendFriendsScreen(recommendFriendsList: List<RecommendFriendUiModel> = listOf()) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        RecommendFriendsHeader(recommendFriendsList.size)
        Spacer(modifier = Modifier.height(7.dp))

        /* TODO: ????????? ????????? ???????????? ???????????? */
        if (recommendFriendsList.isEmpty()) {
            RecommendFriendsPermissionBody()
        } else {
            RecommendFriendsListBody(recommendFriendsList)
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

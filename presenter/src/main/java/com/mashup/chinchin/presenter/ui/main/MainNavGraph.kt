package com.mashup.chinchin.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.main.HomeScreen
import com.mashup.chinchin.presenter.main.MoreScreen
import com.mashup.chinchin.presenter.main.RecommendFriendsScreen
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    recommendFriends: List<FriendUiModel>,
    showBottomSheet: () -> Unit,
    onSelectFriend: (friend: FriendUiModel) -> Unit,
    bottomPaddingValue: Dp
) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen(bottomPaddingValue = bottomPaddingValue)
        }
        composable(route = MainNavScreen.RecommendFriends.route) {
            RecommendFriendsScreen(
                recommendFriends,
                showBottomSheet,
                onSelectFriend,
                bottomPaddingValue = bottomPaddingValue,
                onClickMore = {}
            )
        }
        composable(route = MainNavScreen.More.route) {
            MoreScreen()
        }
    }
}

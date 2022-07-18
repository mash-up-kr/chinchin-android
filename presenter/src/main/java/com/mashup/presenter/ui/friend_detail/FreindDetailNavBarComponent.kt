package com.mashup.presenter.ui.friend_detail

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.White

@Composable
fun FriendDetailNavBar(
    screens: List<FriendDetailNavScreen>,
    currentDestination: FriendDetailNavScreen,
    onTabSelected: (FriendDetailNavScreen) -> Unit
) {
    BottomNavigation(backgroundColor = White) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                onTabSelected = {
                    onTabSelected(screen)
                }
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: FriendDetailNavScreen,
    currentDestination: FriendDetailNavScreen,
    onTabSelected: () -> Unit,
) {
    BottomNavigationItem(
        icon = {
            Text(
                text = screen.title
            )
        },
        selectedContentColor = Black,
        unselectedContentColor = Color(0xFFD9D9D9),
        selected = currentDestination == screen,
        onClick = { onTabSelected() }
    )
}
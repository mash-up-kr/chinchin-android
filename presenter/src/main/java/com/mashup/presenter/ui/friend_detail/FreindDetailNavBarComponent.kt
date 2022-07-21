package com.mashup.presenter.ui.friend_detail

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.Gray_400
import com.mashup.presenter.ui.theme.White

@Composable
fun FriendDetailNavBar(
    screens: List<FriendDetailNavScreen>,
    currentDestination: FriendDetailNavScreen,
    onTabSelected: (FriendDetailNavScreen) -> Unit,
) {
    //TODO 선택된 아이템 하단 줄 표시
    BottomNavigation(
        backgroundColor = White, elevation = 0.dp
    ) {
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
        unselectedContentColor = Gray_400,
        selected = currentDestination == screen,
        onClick = { onTabSelected() },
    )
}
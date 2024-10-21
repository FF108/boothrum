package com.isenap5.boothrum.data.model
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.isenap5.boothrum.domain.Routes


enum class NavigationItem (
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    Home (
        icon = Icons.Outlined.Home,
        title = "Home",
        route = Routes.HOME
    ),
    Favorite(
        icon = Icons.Default.FavoriteBorder,
        title = "Favourite",
        route = Routes.FAVOURITE
    ),
    Boards (
        icon = Icons.Outlined.Menu,
        title = "Boards",
        route = Routes.BOARDS
    ),
    Settings (
        icon = Icons.Outlined.Settings,
        title = "Settings",
        route = Routes.SETTINGS
    ),
    About (
        icon = Icons.Outlined.Info,
        title = "About",
        route = Routes.ABOUT
    )
}
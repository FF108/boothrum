package com.isenap5.boothrum.domain.model
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.isenap5.boothrum.R


enum class NavigationItem (
    val title: String,
    val icon: ImageVector
) {
    Home (
        icon = Icons.Outlined.Home,
        title = "Home"
    ),
    Favorite(
        icon = Icons.Default.FavoriteBorder,
        title = "Favourite"
    ),
    Boards (
        icon = Icons.Outlined.Menu,
        title = "Boards"
    ),
    Settings (
        icon = Icons.Outlined.Settings,
        title = "Settings"
    ),
    About (
        icon = Icons.Outlined.Info,
        title = "About"
    )
}
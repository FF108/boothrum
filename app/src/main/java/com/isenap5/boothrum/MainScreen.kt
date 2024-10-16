package com.isenap5.boothrum

import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.isenap5.boothrum.domain.model.CustomDrawerState
import com.isenap5.boothrum.domain.model.NavigationItem
import com.isenap5.boothrum.domain.model.isOpened
import com.isenap5.boothrum.domain.model.opposite
import com.isenap5.boothrum.domain.model.SearchBarState
import com.isenap5.boothrum.presentation.component.CustomDrawer
import com.isenap5.boothrum.util.coloredShadow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlin.math.roundToInt
import androidx.navigation.compose.rememberNavController
import com.isenap5.boothrum.domain.Routes
import com.isenap5.boothrum.presentation.component.ImageBoardViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ImageBoardViewModel) {

    var drawerState by rememberSaveable() { mutableStateOf(CustomDrawerState.Closed) }
    var selectedNavigationItem by rememberSaveable() { mutableStateOf(NavigationItem.Home) }

    var searchBarState by rememberSaveable() { mutableStateOf(SearchBarState.Closed) }

    val navController = rememberNavController()

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        CustomDrawer(
            selectedNavigationItem = selectedNavigationItem,
            onNavigationItemClick = {
                selectedNavigationItem = it
                navController.navigate(it.route)
                drawerState = CustomDrawerState.Closed
            },
            onCloseClick = { drawerState = CustomDrawerState.Closed }
        )
        Scaffold(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 50.dp
                )
                .clickable(enabled = drawerState == CustomDrawerState.Opened) {
                    drawerState = CustomDrawerState.Closed
                }
            ,
            topBar = {
                TopAppBar(
                    title = { Text(text = selectedNavigationItem.title) },
                    navigationIcon = {
                        IconButton(
                            onClick = { drawerState = drawerState.opposite() }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu Icon"
                            )
                        }
                    }
                )
            },
            content = {
                    NavHost(navController = navController, startDestination = Routes.ABOUT, builder = {
                        composable(Routes.HOME) {
                            HomeScreen(
                                viewModel,
                                searchBarState,
                                { searchBarState = it })
                        }
                        composable(Routes.BOARDS) { BoardsScreen() }
                        composable(Routes.FAVOURITE) { FavouriteScreen() }
                        composable(Routes.SETTINGS) { SettingsScreen() }
                        composable(Routes.ABOUT) { AboutScreen() }
                    })
            }
        )
    }
}
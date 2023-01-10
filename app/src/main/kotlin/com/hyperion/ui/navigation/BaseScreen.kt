package com.hyperion.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.hyperion.R
import dev.olshevski.navigation.reimagined.*

private fun <T> NavController<T>.switchTo(destination: T) {
    if (!moveToTop { it == destination }) navigate(destination)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseScreen(
    windowSizeClass: WindowSizeClass,
    hideNavLabel: Boolean,
    onClickSearch: () -> Unit,
    onClickSettings: () -> Unit,
    content: @Composable (BaseDestination) -> Unit
) {
    val navController = rememberNavController(BaseDestination.HOME)
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(stringResource(navController.currentDestination.label))
                },
                actions = {
                    IconButton(onClick = onClickSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search)
                        )
                    }
                    IconButton(onClick = onClickSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (
                windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact &&
                windowSizeClass.heightSizeClass != WindowHeightSizeClass.Compact
            ) {
                NavigationBar {
                    BaseDestination.values().forEach { destination ->
                        NavigationBarItem(
                            selected = navController.currentDestination == destination,
                            icon = { Icon(destination.icon, null) },
                            label = if (!hideNavLabel) {
                                { Text(stringResource(destination.label)) }
                            } else null,
                            onClick = { navController.switchTo(destination) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
                NavigationRail {
                    BaseDestination.values().forEach { destination ->
                        NavigationRailItem(
                            selected = navController.currentDestination == destination,
                            icon = { Icon(destination.icon, null) },
                            label = if (!hideNavLabel) {
                                { Text(stringResource(destination.label)) }
                            } else null,
                            onClick = { navController.switchTo(destination) }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
            ) {
                AnimatedNavHost(
                    controller = navController
                ) { destination ->
                    content(destination)
                }
            }
        }
    }
}
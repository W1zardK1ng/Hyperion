package com.hyperion.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.hyperion.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
enum class RootDestination(
    val icon: @RawValue ImageVector,
    @StringRes val label: Int
) : Destination {
    HOME(Icons.Default.Home, R.string.home),
    FEED(Icons.Default.Subscriptions, R.string.feed),
    LIBRARY(Icons.Default.VideoLibrary, R.string.library)
}
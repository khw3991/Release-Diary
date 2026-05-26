package com.example.mydiary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "HOME", Icons.Default.Home)
    object Calendar : Screen("calendar", "CALENDAR", Icons.Default.CalendarMonth)
    object Info : Screen("info", "INFO", Icons.Default.Info)
}

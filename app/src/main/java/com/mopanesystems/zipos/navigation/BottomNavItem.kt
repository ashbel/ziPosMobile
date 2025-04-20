package com.mopanesystems.zipos.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Default.Home)
    object Sales : BottomNavItem("sales", "Sales", Icons.Default.ShoppingCart)
    object Inventory : BottomNavItem("products", "Products", Icons.Default.Menu)
    object Chat : BottomNavItem("chat", "Chat", Icons.Default.Home)
}

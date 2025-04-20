package com.mopanesystems.zipos.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun DashboardScreen(onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome, Admin", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            QuickButton("New Sale", Icons.Default.ShoppingCart)
            QuickButton("Scan", Icons.Default.Phone)
        }

        Spacer(Modifier.height(16.dp))

        Card {
            Column {
                Text("Today's Sales", fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
                Text("₹12,450 | 24 Transactions")
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Top Products")
        LazyRow {
//            items(topProducts) {
//                ProductCard(it)
//            }
        }
    }
}

@Composable
fun <ImageVector> QuickButton(s: String, shoppingCart: ImageVector) {

}

package com.mopanesystems.zipos.presentation.inventory.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.presentation.inventory.viewmodel.InventoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel = hiltViewModel(),
    onAddClick: () -> Unit = {},
    onEditClick: (Product) -> Unit = {},
    navController: NavHostController,) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventory") },
                actions = {
                    IconButton(onClick = { navController.navigate("addProduct") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Product")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn( modifier = Modifier
            .fillMaxSize()
            .padding(padding), userScrollEnabled = true) {
            items(products) { product ->
                Log.d("TAG", "Product(id='${product.id}', name='${product.name}'")
                ProductRow(product, onEditClick, viewModel::onDeleteProduct)
            }
        }
    }
}

@Composable
fun ProductRow(
    product: Product,
    onEdit: (Product) -> Unit,
    onDelete: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEdit(product) }
            .padding(16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(product.name, style = MaterialTheme.typography.titleMedium)
            Text("Qty: ${product.quantityInStock} • \$${product.price}", style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(onClick = { onDelete(product.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }

}
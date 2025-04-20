package com.mopanesystems.zipos.presentation.inventory.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mopanesystems.zipos.presentation.inventory.viewmodel.InventoryViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInventoryScreen(
    productId: String? = null,
    onSaved: () -> Unit,
    viewModel: InventoryViewModel = hiltViewModel()
) {
    val existing = viewModel.productDto

    var name by remember { mutableStateOf(existing.name ?: "") }
    var price by remember { mutableStateOf(existing.price.toString() ?: "") }
    var quantityInStock by remember { mutableStateOf(existing.quantityInStock.toString() ?: "") }
    var barcode by remember { mutableStateOf(existing.barcode ?: "") }
    var imageUrl by remember { mutableStateOf(existing.imageUrl ?: "") }

    LaunchedEffect(productId) {
        productId?.let { viewModel.loadProduct(it) }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (productId == null) "Add Product" else "Edit Product") })
        }) { padding ->

    Column(modifier = Modifier.padding(padding).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { input ->
                if (input.matches(Regex("^\\d*\\.?\\d{0,2}\$"))) {
                    price = input
                }
            },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = quantityInStock,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    quantityInStock = input
                }
            },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = barcode,
            onValueChange = { barcode = it },
            label = { Text("BarCode") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        imageUrl.let {
            OutlinedTextField(
                value = it,
                onValueChange = { imageUrl = it },
                label = { Text("ImageUrl") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            Log.d("TAG", "Product(id='${productId}', name='${UUID.randomUUID()}'")
            viewModel.productDto = existing.copy(
                id = productId ?: "",
                name = name,
                price = price.toDoubleOrNull() ?: 0.0,
                quantityInStock = quantityInStock.toIntOrNull() ?: 0,
                barcode = barcode,
                imageUrl = imageUrl
            )
            viewModel.saveProduct(onSaved)
        }) {
            Text( "Add Product")
        }
    }
}
}


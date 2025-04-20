package com.mopanesystems.zipos.ui.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun EditProductScreen(
    productId: String,
    productViewModel: ProductViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val product = productViewModel.getProductById(productId)
    var name by remember { mutableStateOf(product?.name) }
    var price by remember { mutableStateOf(product?.price.toString()) }
    var quantity by remember { mutableStateOf(product?.quantityInStock.toString()) }

    Column(Modifier.padding(16.dp)) {
        Text("Edit Product", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        name?.let { OutlinedTextField(it, onValueChange = { name = it }, label = { Text("Name") }) }
        OutlinedTextField(price, onValueChange = { price = it }, label = { Text("Price") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(quantity, onValueChange = { quantity = it }, label = { Text("Stock") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            val updated = name?.let {
                product?.copy(
                    name = it,
                    price = price.toDoubleOrNull() ?: product.price,
                    quantityInStock = quantity.toIntOrNull() ?: product.quantityInStock
                )
            }
            //onSave(updated)
        }) {
            Text("Save")
        }
    }
}


package com.mopanesystems.zipos.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mopanesystems.zipos.domain.Product

@Composable
fun ProductScreen(viewModel: ProductViewModel, navController: NavHostController, onCheckout: () -> Unit) {
    val products = viewModel.products
    val cartItems = viewModel.cartItems
    var editingProduct by remember { mutableStateOf<Product?>(null) }
    LaunchedEffect(Unit) {
        if (viewModel.products.isEmpty()) {
            viewModel.seedFakeProducts()
        }
    }

    Row(Modifier.fillMaxSize()) {
        Column(Modifier.weight(1f).padding(8.dp)) {
            Text("Products", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(products) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable { navController.navigate("editProduct/${product.id}") }
                    ) {
                        Row(Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(product.name)
                                Text("₹${product.price}", style = MaterialTheme.typography.bodySmall)
                            }
                            Text("In Stock: ${product.quantityInStock}")
                        }
                    }
                }
            }
        }

        Divider(Modifier.fillMaxHeight().width(1.dp).background(Color.Gray))

        Column(Modifier.weight(1f).padding(8.dp)) {
            Text("Cart", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(cartItems) { cartItem ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${cartItem.product.name} x${cartItem.quantity}")
                        Text("₹${cartItem.totalPrice}")
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Text("Total: ₹${viewModel.getCartTotal()}", fontWeight = FontWeight.Bold)

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCheckout,
                enabled = cartItems.isNotEmpty()
            ) {
                Text("Proceed to Checkout")
            }
        }
    }

//    if (editingProduct != null) {
//        EditProductScreen(
//            product = editingProduct!!,
//            onSave = {
//                viewModel.updateProduct(it)
//                editingProduct = null
//            }
//        )
//    }
}

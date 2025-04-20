package com.mopanesystems.zipos.ui.screens.products

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopanesystems.zipos.data.database.ProductDatabase
import com.mopanesystems.zipos.data.firestore.FirestoreService
import com.mopanesystems.zipos.domain.CartItem
import com.mopanesystems.zipos.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mopanesystems.zipos.domain.ProductEntity

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    private val dao = ProductDatabase.getInstance(application).productDao()
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> get() = _products

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    init {
        viewModelScope.launch {
            dao.getAllProducts().collect { entities ->
                _products.clear()
                _products.addAll(entities.map { it.toProduct() })
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            dao.insertProduct(ProductEntity.fromProduct(product))
        }
    }
    fun getProductById(productId: String): Product? {
        return _products.find { it.id == productId }
    }

    fun seedFakeProducts() {
        viewModelScope.launch {
            val fake = listOf(
                Product(name = "Milk", price = 30.0, quantityInStock = 10),
                Product(name = "Bread", price = 25.0, quantityInStock = 8)
            )
            dao.insertProducts(fake.map { ProductEntity.fromProduct(it) })
        }
    }

    fun addToCart(product: Product) {
        val existing = _cartItems.find { it.product.id == product.id }
        if (existing != null) {
            existing.quantity++
        } else {
            _cartItems.add(CartItem(product))
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: String, quantity: Int) {
        _cartItems.find { it.product.id == productId }?.quantity = quantity
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            dao.insertProduct(ProductEntity.fromProduct(product))
            FirestoreService.pushProduct(product)
        }
    }

    fun getCartTotal(): Double = _cartItems.sumOf { it.totalPrice }

    fun syncFromFirestore() {
        FirestoreService.fetchAllProducts { productList : List<Product> ->
            viewModelScope.launch {
                val entities = productList.map { ProductEntity.fromProduct(it) }
                dao.insertProducts(entities)
            }
        }
    }

}

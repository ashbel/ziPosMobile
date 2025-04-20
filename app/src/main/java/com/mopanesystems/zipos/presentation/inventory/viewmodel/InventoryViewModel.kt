package com.mopanesystems.zipos.presentation.inventory.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.domain.usecase.inventory.AddProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.AddProductsUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.DeleteProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.GetAllProductsUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.GetByIdProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getAllProducts: GetAllProductsUseCase,
    private val addProduct: AddProductUseCase,
    private val addProducts: AddProductsUseCase,
    private val updateProduct: UpdateProductUseCase,
    private val getProductById: GetByIdProductUseCase,
    private val deleteProduct: DeleteProductUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()
    var productDto: Product = Product(
        id = "",
        name = "",
        price = 0.0,
        quantityInStock = 0,
        barcode = "",
        imageUrl = ""
    )
    val products: StateFlow<List<Product>> = getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onAddProduct(product: Product) {
        viewModelScope.launch { addProduct(product) }
    }

    fun onDeleteProduct(id: String) {
        Log.d("TAG", "onDeleteProduct: ")
        viewModelScope.launch { deleteProduct(id) }
    }

    fun onUpdateProduct(product: Product) {
        viewModelScope.launch { updateProduct(product) }
    }

    fun onGetProductById(id: String): StateFlow<Product?> {
        return getProductById(id)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }

    fun onAddProducts(products: List<Product>) {
        viewModelScope.launch { addProducts(products) }
    }

    fun getProductById(id: String): StateFlow<Product?> {
        return getProductById(id)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }

    fun loadProduct(id: String) {
        viewModelScope.launch {
            val product = getProductById(id)
            _uiState.value = uiState.value.copy(isLoading = true)
            product.collect { p ->
                Log.d("TAG", "loadProduct: $p")
                if (p != null) {
                    productDto = p
                    _uiState.value = ProductDetailUiState(product = p, isLoading = false)
                }
            }
        }
    }

    fun saveProduct(onSaved: () -> Unit) {
        viewModelScope.launch {
            if (productDto.id.isEmpty()) {
                Log.d("ADD", "Product(id='${productDto.id}', name='${productDto.name}'")
                productDto = productDto.copy(id = UUID.randomUUID().toString())
                addProduct(productDto)
            } else {
                Log.d("UPDATE", "Product(id='${productDto.id}', name='${productDto.name}'")
                updateProduct(productDto)
            }
            onSaved()
        }
    }

}
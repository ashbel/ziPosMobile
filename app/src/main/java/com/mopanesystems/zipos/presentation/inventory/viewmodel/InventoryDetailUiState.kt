package com.mopanesystems.zipos.presentation.inventory.viewmodel

import com.mopanesystems.zipos.domain.model.inventory.Product

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false
)

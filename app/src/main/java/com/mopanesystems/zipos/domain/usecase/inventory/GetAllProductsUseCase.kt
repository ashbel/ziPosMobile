package com.mopanesystems.zipos.domain.usecase.inventory

import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()
}
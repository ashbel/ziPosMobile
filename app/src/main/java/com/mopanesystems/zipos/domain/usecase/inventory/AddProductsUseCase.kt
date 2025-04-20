package com.mopanesystems.zipos.domain.usecase.inventory

import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository

class AddProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(products: List<Product>) = repository.addProducts(products)
}
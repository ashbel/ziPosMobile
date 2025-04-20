package com.mopanesystems.zipos.domain.usecase.inventory

import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository

class AddProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) = repository.addProduct(product)
}
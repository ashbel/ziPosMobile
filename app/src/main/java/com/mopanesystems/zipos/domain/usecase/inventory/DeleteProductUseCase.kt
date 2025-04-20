package com.mopanesystems.zipos.domain.usecase.inventory

import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository

class DeleteProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteProduct(id)
}
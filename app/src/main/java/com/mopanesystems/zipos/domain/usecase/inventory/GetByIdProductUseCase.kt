package com.mopanesystems.zipos.domain.usecase.inventory

import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository

class GetByIdProductUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(id: String) = repository.getProductById(id)
}
package com.mopanesystems.zipos.domain.repository.inventory

import com.mopanesystems.zipos.domain.model.inventory.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(id: String)
    suspend fun updateProduct(product: Product)
    fun getProductById(id: String): Product?
    suspend fun addProducts(products: List<Product>)
    suspend fun syncWithRemote()
}
package com.mopanesystems.zipos.data.repository.inventory

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mopanesystems.zipos.data.local.inventory.ProductDao
import com.mopanesystems.zipos.data.mapper.inventory.toDomain
import com.mopanesystems.zipos.data.mapper.inventory.toEntity
import com.mopanesystems.zipos.data.mapper.inventory.toEntityList
import com.mopanesystems.zipos.domain.model.inventory.Product
import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao,
    private val firestore: FirebaseFirestore
) : ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun addProduct(product: Product) {
        dao.insert(product.toEntity())
        firestore.collection("products").add(product)
            .addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    override suspend fun addProducts(products: List<Product>) {
        dao.insert(products.toEntityList())
    }

    override suspend fun deleteProduct(id: String) {
        dao.getAll().map { list ->
            list.find { it.id == id }?.let { dao.delete(it) }
            firestore.collection("products").document(id).delete()
        }
    }

    override suspend fun updateProduct(product: Product) {
        dao.update(product.toEntity())
        //firestore.collection("products").document(product.id).update(product)
    }

    override fun getProductById(id: String) : Product? =
        dao.getById(id)?.toDomain()

    override suspend fun syncWithRemote() {
        val snapshot = firestore.collection("products").get().await()
        val remoteProducts = snapshot.documents.mapNotNull { it.toObject(Product::class.java) }
        remoteProducts.forEach { dao.insert(it.toEntity()) }
    }
}
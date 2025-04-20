package com.mopanesystems.zipos.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: Double,
    val quantityInStock: Int,
    val barcode: String = "",
    val imageUrl: String? = null
) {
    fun toProduct(): Product = Product(id, name, price, quantityInStock, barcode, imageUrl)

    companion object {
        fun fromProduct(product: Product): ProductEntity {
            return ProductEntity(
                id = product.id,
                name = product.name,
                price = product.price,
                quantityInStock = product.quantityInStock,
                barcode = product.barcode,
                imageUrl = product.imageUrl
            )
        }
    }
}

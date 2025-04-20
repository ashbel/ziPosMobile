package com.mopanesystems.zipos.data.mapper.inventory

import com.mopanesystems.zipos.data.local.inventory.ProductEntity
import com.mopanesystems.zipos.domain.model.inventory.Product

fun ProductEntity.toDomain(): Product = Product(id, name, price, quantityInStock,barcode,imageUrl)
fun Product.toEntity(): ProductEntity = ProductEntity(id, name, price,  quantityInStock,barcode,imageUrl)
fun List<ProductEntity>.toDomainList(): List<Product> = map { it.toDomain() }
fun List<Product>.toEntityList(): List<ProductEntity> = map { it.toEntity() }
package com.mopanesystems.zipos.domain

import java.util.UUID

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val price: Double = 0.0,
    val quantityInStock: Int = 0,
    val barcode: String = "",
    val imageUrl: String? = null
)

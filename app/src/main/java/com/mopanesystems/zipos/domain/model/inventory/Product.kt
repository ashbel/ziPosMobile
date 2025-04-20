package com.mopanesystems.zipos.domain.model.inventory

import java.util.UUID


data class Product(
    val id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var price: Double = 0.0,
    var quantityInStock: Int = 0,
    var barcode: String = "",
    var imageUrl: String? = null

)
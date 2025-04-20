package com.mopanesystems.zipos.domain

import com.mopanesystems.zipos.domain.model.inventory.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) {
    val totalPrice: Double get() = product.price * quantity
}

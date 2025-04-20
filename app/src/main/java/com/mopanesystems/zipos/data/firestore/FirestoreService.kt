package com.mopanesystems.zipos.data.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.mopanesystems.zipos.domain.Product

object FirestoreService {
    private val db = FirebaseFirestore.getInstance()

    fun fetchAllProducts(onResult: (List<Product>) -> Unit) {
        db.collection("products").get()
            .addOnSuccessListener { snapshot ->
                val products = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(id = doc.id)
                }
                onResult(products)
            }
    }

    fun pushProduct(product: Product) {
        db.collection("products")
            .document(product.id)
            .set(product)
    }
}

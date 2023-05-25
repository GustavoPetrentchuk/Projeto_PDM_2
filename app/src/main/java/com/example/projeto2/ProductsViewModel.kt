package com.example.projeto2

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import data.Product
import data.ProductsRepo

class ProductsViewModel : ViewModel() {

    private val productList = mutableStateListOf<Product>()
    val products: List<Product> get() = productList.sortedByDescending { it.isFavorite }

    init {
        productList.addAll(ProductsRepo.getProducts())
    }

    fun addProduct(product: Product) {
        productList.add(product)
    }

    fun removeProduct(product: Product) {
        productList.remove(product)
    }

    fun toggleFavorite(product: Product) {
        val index = productList.indexOf(product)
        if (index != -1) {
            val updatedProduct = product.copy(isFavorite = !product.isFavorite)
            productList[index] = updatedProduct
        }
    }
}

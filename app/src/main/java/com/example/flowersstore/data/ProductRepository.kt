package com.example.flowersstore.data

import com.example.flowersstore.data.model.Product

class ProductRepository {
    companion object {
        val products: List<Product>

        init {
            products = ArrayList()

            for (i in 1 until 25) {
                products += Product(
                    id = i,
                    title = "Product#$i",
                    preview = "https://via.placeholder.com/150 ",
                    price = (500..10000).random(),
                    productionTime = (3..5).random()
                )
            }
        }
    }

    fun getProducts(): List<Product> {
        return products
    }
}
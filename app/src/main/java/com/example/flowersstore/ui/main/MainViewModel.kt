package com.example.flowersstore.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowersstore.data.ProductRepository
import com.example.flowersstore.data.model.Product

class MainViewModel : ViewModel() {
    private val repository = ProductRepository()

    val products = MutableLiveData<List<Product>>(null)

    fun loadMoreProducts() {
        val task = ProductLoadThread(repository) {
            val existed = products.value

            if (existed == null) products.postValue(it)
            else products.postValue(existed + it)
        }

        val thread = Thread(task)
        thread.start()
    }

    class ProductLoadThread(
        private val repository: ProductRepository,
        private val listener: (List<Product>) -> Unit
    ) : Runnable {

        override fun run() {
            Thread.sleep(300)

            val result = repository.getProducts()
            listener.invoke(result)
        }
    }
}
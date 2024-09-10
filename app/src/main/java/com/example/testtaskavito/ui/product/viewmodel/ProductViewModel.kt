package com.example.testtaskavito.ui.product.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.domain.product.ProductRepositoryFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel (
    private val productRepositoryFlow: ProductRepositoryFlow
): ViewModel() {

    val selectedProduct: StateFlow<Product?> = productRepositoryFlow.getProductFlow()
}
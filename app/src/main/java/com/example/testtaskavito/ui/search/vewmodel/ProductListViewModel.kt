package com.example.testtaskavito.ui.search.vewmodel

import android.content.ContentValues.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskavito.R
import com.example.testtaskavito.domain.debugLog
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.domain.productlist.ProductListInteractor
import com.example.testtaskavito.domain.productlist.ProductListRepository
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productListInteractor: ProductListInteractor
): ViewModel() {

    private val stateLiveData = MutableLiveData<ProductState>()
    fun observeState(): LiveData<ProductState> = stateLiveData

    init {
        loadCountry()
    }

    private fun loadCountry() {
        renderState(ProductState.Loading)
        viewModelScope.launch {
            productListInteractor.searchListProduct()
                .collect { pair ->
                    processResult(pair.first, pair.second)
                    debugLog(TAG) {
                        "Список стран: countryName = ${pair.first?.map { it.name }}, " +
                                "countryId = ${pair.first?.map { it.id }}\n"
                    }
                }
        }
    }

    private fun processResult(productList: List<Product>?, errorMessage: Int?) {
        when {
            errorMessage != null -> {
                renderState(
                    ProductState.Error(
                        message = R.string.nothing_found.toString()
                    )
                )
            }

            else -> {
                renderState(
                    ProductState.Content(
                        content = productList!!
                    )
                )
            }
        }
    }

    private fun renderState(productState: ProductState) {
        stateLiveData.postValue(productState)
    }
}
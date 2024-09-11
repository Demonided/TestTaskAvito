package com.example.testtaskavito.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.FragmentProductBinding
import com.example.testtaskavito.domain.debugLog
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.ui.product.viewmodel.ProductViewModel
import com.example.testtaskavito.ui.search.ProductListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment: Fragment() {

    private val productViewModel by viewModel<ProductViewModel>()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter()

        binding.productRecyclerViewImage.layoutManager =
            LinearLayoutManager(requireContext())
        binding.productRecyclerViewImage.adapter = productAdapter

        lifecycleScope.launchWhenStarted {
            productViewModel.selectedProduct.collect { product ->
                productAdapter.imageProduct.clear()
                product?.images?.let { productAdapter.imageProduct.addAll(it) }
                productAdapter.notifyDataSetChanged()

                debugLog(TAG) {
                    "Список фото = ${product?.images}"
                }

                displayProductInfo(product)
            }
        }

        binding.productBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun displayProductInfo(product: Product?) {
        // Заполнение UI данными о продукте
        binding.productName.text = product?.name
        binding.productCurrentPrice.text = product?.price.toString()
        binding.productPreviousPrice.text = product?.discountedPrice.toString()

        // Характеристики
        binding.productState.text = product?.productSpecifications
            ?.find { it?.key == "Type" }
            ?.value ?: "Нет информации о состоянии"
        binding.productSize.text = product?.productSpecifications
            ?.find { it?.key == "Bed Size" }
            ?.value ?: "Нет информации о размере"
        binding.productBodyMaterial.text = product?.productSpecifications
            ?.find { it?.key == "Mattress Included" }
            ?.value ?: "Нет информации о материале"
        binding.productBrand.text = product?.productSpecifications
            ?.find { it?.key == "Brand" }
            ?.value ?: "Нет информации о бренде"
        binding.productColor.text = product?.productSpecifications
            ?.find { it?.key == "Primary Color" }
            ?.value ?: "Нет информации о цвете"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val SAVE_PRODUCT = "SAVE_PRODUCT"

        private const val TAG = "ProductFragment"

        fun createArgs(product: Product): Bundle {
            return bundleOf(SAVE_PRODUCT to product)
        }
    }
}
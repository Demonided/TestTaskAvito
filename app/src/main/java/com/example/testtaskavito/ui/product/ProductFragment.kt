package com.example.testtaskavito.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testtaskavito.databinding.FragmentProductBinding
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.ui.product.viewmodel.ProductViewModel
import com.example.testtaskavito.ui.search.vewmodel.ProductListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment: Fragment() {

    private val productViewModel by viewModel<ProductViewModel>()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

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

//        val selectedProduct: Product? = arguments?.getParcelable(SAVE_PRODUCT)
//        selectedProduct?.let { productViewModel.initState(it) }

        binding.productBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val SAVE_PRODUCT = "SAVE_PRODUCT"

        fun createArgs(product: Product): Bundle {
            return bundleOf(SAVE_PRODUCT to product)
        }
    }
}
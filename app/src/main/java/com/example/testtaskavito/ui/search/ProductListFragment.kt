package com.example.testtaskavito.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.FragmentSearchBinding
import com.example.testtaskavito.domain.debugLog
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.ui.search.vewmodel.ProductListViewModel
import com.example.testtaskavito.ui.search.vewmodel.ProductState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private val viewModel by viewModel<ProductListViewModel>()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productListAdapter = ProductListAdapter()
        productListAdapter.itemClickListener = { _, item ->
            viewModel.setProductInfo(item)

            findNavController().navigate(
                R.id.action_searchFragment_to_productFragment
            )
        }

        binding.searchRecyclerListProduct.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.searchRecyclerListProduct.adapter = productListAdapter

        viewModel.observeState().observe(viewLifecycleOwner) { state ->
            debugLog(TAG) {
                "Наш state = $state"
            }

            when (state) {
                is ProductState.Content -> {
                    showContent()

                    productListAdapter.productList.clear() // Очистка списка перед добавлением новых данных (если нужно)
                    productListAdapter.productList.addAll(state.content)
                    productListAdapter.notifyDataSetChanged() // Уведомление адаптера об изменении данных
                }

                is ProductState.Loading -> showLoading()
                is ProductState.Empty -> showEmpty("No")
                is ProductState.Error -> showError("Ошибочка")
            }
        }
    }

    private fun showContent() {
        binding.searchRecyclerListProduct.visibility = View.VISIBLE
        binding.searchProgressBar.visibility = View.GONE
    }

    private fun showLoading() {
        binding.searchRecyclerListProduct.visibility = View.GONE
        binding.searchProgressBar.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {
        // добавить холдер отображающий состояние
        binding.searchRecyclerListProduct.visibility = View.GONE
        binding.searchProgressBar.visibility = View.GONE
    }

    private fun showEmpty(message: String) {
        // добавить холдер отображающий состояние
        binding.searchRecyclerListProduct.visibility = View.GONE
        binding.searchProgressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ProductListFragment"
    }
}
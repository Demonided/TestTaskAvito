package com.example.testtaskavito.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskavito.databinding.FragmentSearchBinding
import com.example.testtaskavito.domain.product.Product
import com.example.testtaskavito.ui.search.vewmodel.ProductListViewModel
import com.example.testtaskavito.ui.search.vewmodel.ProductState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment() {

    private val viewModel by viewModel<ProductListViewModel>()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    lateinit var productListAdapter: ProductListAdapter
    private lateinit var recyclerView: RecyclerView

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

        val adapter = ProductListAdapter()
        adapter.itemClickListener = { _, item ->


            findNavController().popBackStack()
        }

        binding.searchRecyclerListProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.searchRecyclerListProduct.adapter = adapter

        viewModel.observeState().observe(viewLifecycleOwner) { state ->
            when(state) {
                is ProductState.Content -> {
                    showContent()

                    productListAdapter.productList.addAll(state.content)
                }

                is ProductState.Loading -> showLoading()
                is ProductState.Empty -> showEmpty("No")
                is ProductState.Error -> showError("Ошибочка")
            }
        }
    }

    private fun showContent() {
    }

    private fun showLoading() {
    }

    private fun showError(errorMessage: String) {
    }

    private fun showEmpty(message: String) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
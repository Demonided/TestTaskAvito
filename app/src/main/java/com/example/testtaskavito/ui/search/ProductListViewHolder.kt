package com.example.testtaskavito.ui.search

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskavito.databinding.ListProductBinding
import com.example.testtaskavito.domain.product.Product

class ProductListViewHolder(private val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(product: Product) {

        binding.listProductName.text = product.name
        binding.listProductCurrentPrice.text = product.price.toString()
        binding.listProductPreviousPrice.text = product.discountedPrice.toString()

        Glide
            .with(itemView)
            .load(product.images[0])
    }
}
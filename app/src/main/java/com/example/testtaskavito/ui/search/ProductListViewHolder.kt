package com.example.testtaskavito.ui.search

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.ListProductBinding
import com.example.testtaskavito.domain.product.Product

class ProductListViewHolder(private val binding: ListProductBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(product: Product) {

        binding.listProductName.text = product.name
        binding.listProductCurrentPrice.text = product.price.toString()
        binding.listProductPreviousPrice.text = product.discountedPrice.toString()

        if (product.images.isNotEmpty()) {
            Glide
                .with(itemView.context) // используем context itemView для корректной работы Glide
                .load(product.images[0]) // загружаем первое изображение из списка
                .into(binding.listProductImage) // указываем ImageView, куда будет загружено изображение
        } else {
            // Установите placeholder или заглушку на случай отсутствия изображений
            binding.listProductImage.setImageResource(R.drawable.logo_placeholder) // замените на ваш placeholder
        }
    }
}
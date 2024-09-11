package com.example.testtaskavito.ui.product

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.ImageProductBinding
import com.example.testtaskavito.domain.debugLog

class ProductViewHolder(private val binding: ImageProductBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("CheckResult")
    fun bind(listImage: String) {

        debugLog(TAG) {
            "Строка ProductViewHolder = $listImage"
        }

        if (listImage.isNotEmpty()) {
            Glide
                .with(itemView.context) // используем context itemView для корректной работы Glide
                .load(listImage) // загружаем первое изображение из списка
                .into(binding.imageProduct) // указываем ImageView, куда будет загружено изображение
        } else {
            // Установите placeholder или заглушку на случай отсутствия изображений
            binding.imageProduct.setImageResource(R.drawable.logo_placeholder) // замените на ваш placeholder
        }
    }

    companion object {
        const val TAG = "ProductViewHolder"
    }
}
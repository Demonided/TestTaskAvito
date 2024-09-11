package com.example.testtaskavito.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskavito.databinding.ImageProductBinding

class ProductAdapter: RecyclerView.Adapter<ProductViewHolder>() {

    val imageProduct = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ImageProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(imageProduct[position])
    }

    override fun getItemCount(): Int = imageProduct.size

}
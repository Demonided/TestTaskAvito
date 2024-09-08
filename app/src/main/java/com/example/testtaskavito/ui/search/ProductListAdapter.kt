package com.example.testtaskavito.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskavito.databinding.ListProductBinding
import com.example.testtaskavito.domain.product.Product

class ProductListAdapter : RecyclerView.Adapter<ProductListViewHolder>() {

    val productList = ArrayList<Product>()
    var itemClickListener: ((Int, Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener { itemClickListener?.invoke(position, product)}
    }

}
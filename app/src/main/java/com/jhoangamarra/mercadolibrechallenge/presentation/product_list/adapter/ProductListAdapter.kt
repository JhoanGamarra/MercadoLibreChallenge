package com.jhoangamarra.mercadolibrechallenge.presentation.product_list.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhoangamarra.mercadolibrechallenge.R
import com.jhoangamarra.mercadolibrechallenge.databinding.ItemProductBinding
import com.jhoangamarra.mercadolibrechallenge.domain.model.ProductDomainModel


private val TAG = ProductListAdapter::class.java.simpleName

class ProductListAdapter() :
    ListAdapter<ProductDomainModel, ProductListAdapter.ProductViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ProductDomainModel>() {
        override fun areItemsTheSame(
            oldItem: ProductDomainModel,
            newItem: ProductDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductDomainModel,
            newItem: ProductDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }


    lateinit var onItemClickListener: (ProductDomainModel) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }


    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: ProductDomainModel) {

            ItemProductBinding.bind(view).apply {
                name.text = product.title
                price.text = product.price
                Glide.with(image.context)
                    .load(product.productImage)
                    .centerCrop().into(image)
            }

            view.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(product)
                } else {
                    Log.e(TAG, "onItemClickListener not initialized")
                }
            }
        }
    }


}
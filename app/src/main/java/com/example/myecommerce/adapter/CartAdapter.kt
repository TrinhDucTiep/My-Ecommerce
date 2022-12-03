package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.ItemCartProductModel

class CartAdapter(var listItemCartProductModel: MutableList<ItemCartProductModel>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val itemCartProductModel = listItemCartProductModel.get(position)

        holder.tvShopName.text = itemCartProductModel.shopName
        holder.imgProduct.setImageResource(itemCartProductModel.productImage)
        holder.tvProductName.text = itemCartProductModel.productName
        holder.tvProductType.text = itemCartProductModel.productType
        holder.tvProductOldPrice.text = itemCartProductModel.productOldPrice
        holder.tvProductPrice.text = itemCartProductModel.productPrice
    }

    override fun getItemCount(): Int {
        return listItemCartProductModel.size
    }
}

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvShopName: TextView
    var imgProduct: ImageView
    var tvProductName: TextView
    var tvProductType: TextView
    var tvProductOldPrice: TextView
    var tvProductPrice: TextView
    init {
        tvShopName = itemView.findViewById(R.id.tvShopName)
        imgProduct = itemView.findViewById(R.id.imgProduct)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvProductType = itemView.findViewById(R.id.tvProductType)
        tvProductOldPrice = itemView.findViewById(R.id.tvProductOldPrice)
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice)
    }
}
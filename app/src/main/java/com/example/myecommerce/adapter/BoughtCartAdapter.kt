package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.ItemBoughtCartProductModel
import com.example.myecommerce.model.ItemCartProductModel
import com.example.myecommerce.view.fragment.CartFragment

class BoughtCartAdapter(var listItemBoughtCartProductModel: MutableList<ItemBoughtCartProductModel>, var controller: NavController) : RecyclerView.Adapter<BoughtCartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoughtCartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bought_cart, parent, false)
        return BoughtCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoughtCartViewHolder, position: Int) {
        val itemBoughtCartProductModel = listItemBoughtCartProductModel.get(position)

        holder.tvShopName.text = itemBoughtCartProductModel.shopName
        holder.imgProduct.setImageResource(itemBoughtCartProductModel.productImage)
        holder.tvProductName.text = itemBoughtCartProductModel.productName
        //todo: còn status view, tv product status, rating là chưa set đến

        holder.itemView.setOnClickListener {
            controller.navigate(R.id.action_mainFragment_to_detailStatusOrderFragment)
        }
    }

    override fun getItemCount(): Int {
        return listItemBoughtCartProductModel.size
    }
}

class BoughtCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvShopName: TextView
    var imgProduct: ImageView
    var tvProductName: TextView
    var tvProductStatus: TextView
    init {
        tvShopName = itemView.findViewById(R.id.tvShopName)
        imgProduct = itemView.findViewById(R.id.imgProduct)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvProductStatus = itemView.findViewById(R.id.tvStatusDeliver)
    }
}
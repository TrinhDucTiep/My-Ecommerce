package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myecommerce.R
import com.example.myecommerce.model.HorizontalProductModel
import com.example.myecommerce.view.fragment.HomeFragment

class HorizontalProductAdapter(
    var listHorizontalProductModel: MutableList<HorizontalProductModel>,
                               var navController: NavController) : RecyclerView.Adapter<HorizontalProductAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val horizontalProductModel = listHorizontalProductModel.get(position)
//        holder.productImage.setImageResource(listHorizontalProductModel.get(position).productImage)
        Glide.with(holder.itemView.context).load(horizontalProductModel.productImage).placeholder(R.drawable.ic_home).into(holder.productImage)
        holder.productName.text = horizontalProductModel.productName
        holder.productDesc.text = horizontalProductModel.productDesc
        holder.productPrice.text = horizontalProductModel.productPrice

        holder.itemView.setOnClickListener {
            navController.navigate(R.id.detailProductFragment)
        }
    }

    override fun getItemCount(): Int {
        return listHorizontalProductModel.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImage: ImageView
        var productName: TextView
        var productDesc: TextView
        var productPrice: TextView
        init {
            productImage = itemView.findViewById(R.id.imgProduct)
            productName = itemView.findViewById(R.id.tvProductName)
            productDesc = itemView.findViewById(R.id.tvProductDesc)
            productPrice = itemView.findViewById(R.id.tvProductPrice)
        }
    }
}
package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.ProductSpecModel

class ProductSpecAdapter(var listProductSpecModel: MutableList<ProductSpecModel>): RecyclerView.Adapter<ProductSpecViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSpecViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_specification, parent, false)
        return ProductSpecViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductSpecViewHolder, position: Int) {
        holder.featureName.text = listProductSpecModel.get(position).featureName
        holder.featureValue.text = listProductSpecModel.get(position).featureValue
    }

    override fun getItemCount(): Int {
        return listProductSpecModel.size
    }

}

class ProductSpecViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
    var featureName: TextView
    var featureValue: TextView
    init {
        featureName = itemview.findViewById(R.id.tvFeatureName)
        featureValue = itemview.findViewById(R.id.tvFeatureValue)
    }
}
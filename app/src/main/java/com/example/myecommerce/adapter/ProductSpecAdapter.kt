package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.ProductSpecModel

class ProductSpecAdapter(var listTitle: MutableList<String>?, var listValue: MutableList<String>?): RecyclerView.Adapter<ProductSpecViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSpecViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_specification, parent, false)
        return ProductSpecViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductSpecViewHolder, position: Int) {
        holder.featureName.text = listTitle?.get(position)
        holder.featureValue.text = listValue?.get(position)
    }

    override fun getItemCount(): Int {
        if (listTitle.isNullOrEmpty()) return 0
        return listTitle?.size!!
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
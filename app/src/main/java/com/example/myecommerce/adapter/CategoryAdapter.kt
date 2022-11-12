package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myecommerce.R
import com.example.myecommerce.model.CategoryModel

class CategoryAdapter(private var categoryModelList : MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.categoryContent.text = categoryModelList.get(position).categoryContent
//        holder.categoryImageView = categoryModelList.get(position).categoryIconLink
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }
}

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var categoryImageView: ImageView
    var categoryContent: TextView
    init {
        categoryImageView = itemView.findViewById(R.id.img)
        categoryContent = itemView.findViewById(R.id.content)
    }
}
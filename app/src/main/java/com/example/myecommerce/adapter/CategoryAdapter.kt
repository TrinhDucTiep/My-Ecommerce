package com.example.myecommerce.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myecommerce.R
import com.example.myecommerce.model.CategoryModel
import com.example.myecommerce.view.fragment.MainFragmentDirections
import kotlin.contracts.contract

class CategoryAdapter(private var categoryModelList : MutableList<CategoryModel>, var controller: NavController) : RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryModel = categoryModelList.get(position)
        holder.categoryContent.text = categoryModel.categoryContent
//        holder.categoryImageView = categoryModelList.get(position).categoryIconLink
        if (!categoryModel.categoryIconLink.equals("null")) {
            Glide.with(holder.itemView.context).load(categoryModel.categoryIconLink).placeholder(R.drawable.ic_home).into(holder.categoryImageView)
        }

        if (categoryModel.index != 1) {
            val bundle = bundleOf("content" to categoryModel.categoryContent)
            holder.itemView.setOnClickListener {
                controller.navigate(R.id.action_mainFragment_to_detailCategoryFragment, bundle)
            }
        }
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
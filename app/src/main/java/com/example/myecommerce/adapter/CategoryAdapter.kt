package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.recyclerview.widget.RecyclerView
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
        val content = categoryModelList.get(position).categoryContent
        holder.categoryContent.text = content
//        holder.categoryImageView = categoryModelList.get(position).categoryIconLink

        if(position != 0){
            val bundle = bundleOf("content" to content)
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
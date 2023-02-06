package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myecommerce.R

class ImageProductAdapter(var listImgLink: MutableList<String>) : RecyclerView.Adapter<ImageProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return ImageProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageProductViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(listImgLink[position]).placeholder(R.drawable.img_placeholder).into(holder.imgProduct)
    }

    override fun getItemCount(): Int {
        return listImgLink.size
    }

}

class ImageProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgProduct: ImageView
    init {
        imgProduct = itemView.findViewById(R.id.bannerImg)
    }
}
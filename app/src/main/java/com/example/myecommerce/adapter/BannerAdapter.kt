package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.BannerModel

class BannerAdapter(private var listBannerModel: MutableList<BannerModel>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgBanner: ImageView
        init {
            imgBanner = itemView.findViewById(R.id.bannerImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val bannerModel = listBannerModel.get(position)
        holder.imgBanner.setImageResource(bannerModel.bannerId)
    }

    override fun getItemCount(): Int {
        return listBannerModel.size
    }
}
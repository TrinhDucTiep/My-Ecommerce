package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myecommerce.R
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.model.HorizontalProductModel

class GridProductAdapter(var listProduct: MutableList<HorizontalProductModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return GlobalHelper.numberProductInGridView
    }

    override fun getItem(position: Int): Any {
        return listProduct[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mView: View;
        if(convertView == null){
            mView = LayoutInflater.from(parent?.context).inflate(R.layout.item_product_layout, null)
            mView.findViewById<ImageView>(R.id.imgProduct).setImageResource(listProduct.get(position).productImage)
            mView.findViewById<TextView>(R.id.tvProductName).text = listProduct.get(position).productName
            mView.findViewById<TextView>(R.id.tvProductDesc).text = listProduct.get(position).productDesc
            mView.findViewById<TextView>(R.id.tvProductPrice).text = listProduct.get(position).productPrice
        }else{
            mView = convertView
        }
        return mView
    }
}
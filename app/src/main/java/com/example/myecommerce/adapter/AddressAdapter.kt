package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.model.AddressModel

class AddressAdapter(var listAddressModel: MutableList<AddressModel>, var controller: NavController) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)

        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.tvTitle.text = listAddressModel[position].title
        holder.tvAddress.text = listAddressModel[position].address

        holder.imgEdit.setOnClickListener {
            //todo: sau này nhớ điền thông tin vào các edittext ở màn tiếp theo
            controller.navigate(R.id.action_listAddressFragment_to_addAddressFragment)
        }

        holder.itemView.setOnClickListener {
            //todo: nhớ sau này phải chuyển đến các bước tiếp theo
        }
    }

    override fun getItemCount(): Int {
        return listAddressModel.size
    }

    class AddressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvAddress: TextView
        var imgEdit: ImageView
        init {
            tvTitle = itemView.findViewById(R.id.tvTitleAddress)
            tvAddress = itemView.findViewById(R.id.tvContentAddress)
            imgEdit = itemView.findViewById(R.id.imgEdit)
        }
    }

}

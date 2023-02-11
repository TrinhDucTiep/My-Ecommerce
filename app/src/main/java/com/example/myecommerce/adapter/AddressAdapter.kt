package com.example.myecommerce.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myecommerce.R
import com.example.myecommerce.helper.AlertDialogHelper
import com.example.myecommerce.model.AddressModel
import com.example.myecommerce.viewmodel.UserInfoViewModel
import com.google.gson.Gson

class AddressAdapter(var listAddressModel: MutableList<AddressModel>, var controller: NavController, var userInfoViewModel: UserInfoViewModel) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)

        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        var addressModel = listAddressModel[position]
        holder.tvTitle.text = addressModel.title
        holder.tvAddress.text = addressModel.content

        holder.imgEdit.setOnClickListener {
            val bundle = bundleOf("address" to Gson().toJson(addressModel, AddressModel::class.java))
            bundle.putInt("index", position)
            controller.navigate(R.id.action_listAddressFragment_to_addAddressFragment, bundle)
        }

        //setup delete data
        holder.itemView.setOnLongClickListener {
            AlertDialogHelper.showAlertDialog(
                holder.itemView.context,
                "Xoá địa chỉ",
                "Bạn có muốn xoá địa chỉ này không?",
                R.drawable.ic_alert,
                okCallBack = {
                    userInfoViewModel.deleteAddress(
                        position,
                        updateUI = {
                            listAddressModel.removeAt(position)
                            notifyItemRemoved(position)
                            Toast.makeText(holder.itemView.context, "Xoá địa chỉ thành công", Toast.LENGTH_SHORT).show()
                        },
                        updateFail = {
                            Toast.makeText(holder.itemView.context, "Xoá địa chỉ thất bại", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
            return@setOnLongClickListener true
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

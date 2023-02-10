package com.example.myecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myecommerce.R
import com.example.myecommerce.helper.AlertDialogHelper
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.model.DetailProductModel
import com.example.myecommerce.model.ItemCartProductModel
import com.example.myecommerce.model.SumOfMonneyModelLivedata
import com.example.myecommerce.viewmodel.DetailProductViewModel

class CartAdapter(var listDetailProductModel: MutableList<DetailProductModel>,
                  var isAll: Boolean = false,
                  var detailProductViewModel: DetailProductViewModel,
                  var inprogressUI: () -> Unit,
                  var updateUI: () -> Unit) : RecyclerView.Adapter<CartViewHolder>() {
    var sumOfMoney = 0L
    var sumOfMonneyModelLivedata = MutableLiveData<SumOfMonneyModelLivedata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val detailProductModel = listDetailProductModel.get(position)

        holder.tvShopName.text = detailProductModel.idShop
        Glide.with(holder.itemView.context).load(detailProductModel.productImage?.get(0)).placeholder(R.drawable.img_placeholder).into(holder.imgProduct)
        holder.tvProductName.text = detailProductModel.productName
        holder.tvProductType.text = detailProductModel.specValue?.get(1) ?: ""
        holder.tvProductOldPrice.text = detailProductModel.productOldPrice
        holder.tvProductPrice.text = detailProductModel.productPrice

        //onclick
        holder.checkBox.setOnClickListener {
            if (holder.checkBox.isChecked) {
                sumOfMoney += GlobalHelper.getMoneyFromString(detailProductModel.productPrice!!) * holder.numberPicker.value
                sumOfMonneyModelLivedata.postValue(SumOfMonneyModelLivedata(sumOfMoney.toString()) )
            } else {
                sumOfMoney -= GlobalHelper.getMoneyFromString(detailProductModel.productPrice!!) * holder.numberPicker.value
                sumOfMonneyModelLivedata.postValue(SumOfMonneyModelLivedata(sumOfMoney.toString()) )
            }
        }

        holder.numberPicker.setValueChangedListener { value, action ->
            if (holder.checkBox.isChecked)
                if (action.toString().equals("INCREMENT")) {
                    sumOfMoney += GlobalHelper.getMoneyFromString(detailProductModel.productPrice!!)
                    sumOfMonneyModelLivedata.postValue(SumOfMonneyModelLivedata(sumOfMoney.toString()) )
                } else {
                    sumOfMoney -= GlobalHelper.getMoneyFromString(detailProductModel.productPrice!!)
                    sumOfMonneyModelLivedata.postValue(SumOfMonneyModelLivedata(sumOfMoney.toString()) )
                }
        }

        holder.imgTrash.setOnClickListener {
            AlertDialogHelper.showAlertDialog(
                holder.itemView.context,
                holder.itemView.context.getString(R.string.delete_cart_dialog_title),
                holder.itemView.context.getString(R.string.delete_cart_dialog_message),
                R.drawable.ic_alert,
                okCallBack = {
                    detailProductViewModel.deleteItemInCart(
                        detailProductModel.id!!,
                        inprogressUI,
                        updateUI,
                        updateAdapter = {
                            listDetailProductModel.removeAt(position)
                            notifyItemRemoved(position)
                        },
                        holder.itemView.context
                    )
                }
            )
        }

        //isAll = true we have to notifyDataSetChange for all item in apdapter
        if (isAll && !holder.checkBox.isChecked) {
            holder.checkBox.performClick()
        }

    }

    override fun getItemCount(): Int {
        return listDetailProductModel.size
    }
}

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvShopName: TextView
    var imgProduct: ImageView
    var tvProductName: TextView
    var tvProductType: TextView
    var tvProductOldPrice: TextView
    var tvProductPrice: TextView
    var checkBox: CheckBox
    var numberPicker: com.travijuu.numberpicker.library.NumberPicker
    var imgTrash: ImageView
    init {
        tvShopName = itemView.findViewById(R.id.tvShopName)
        imgProduct = itemView.findViewById(R.id.imgProduct)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvProductType = itemView.findViewById(R.id.tvProductType)
        tvProductOldPrice = itemView.findViewById(R.id.tvProductOldPrice)
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice)
        checkBox = itemView.findViewById(R.id.checkbox)
        numberPicker = itemView.findViewById(R.id.numberPicker)
        imgTrash = itemView.findViewById(R.id.imgTrash)
    }
}
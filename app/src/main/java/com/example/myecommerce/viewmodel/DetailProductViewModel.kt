package com.example.myecommerce.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myecommerce.R
import com.example.myecommerce.adapter.CartAdapter
import com.example.myecommerce.helper.GlobalHelper
import com.example.myecommerce.helper.PreferenceHelper
import com.example.myecommerce.model.DetailProductModel
import com.example.myecommerce.model.ItemCartProductModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailProductViewModel : ViewModel() {
    private var firestore = Firebase.firestore
    var detailProductModelLiveData = MutableLiveData<DetailProductModel>()

    //detail product
    fun loadDetailProduct(id: String) : DetailProductModel {

        var tempModel = DetailProductModel()

        firestore.collection("PRODUCTS")
            .document(id)
            .get()
            .addOnSuccessListener { document ->
                tempModel = document.toObject(DetailProductModel::class.java)!!
                detailProductModelLiveData.postValue(tempModel)
            }
            .addOnFailureListener {
                Log.d("firestore_error", "loadDetailProduct: " + it)
            }

        return tempModel
    }

    //cart
    fun loadListProductOnCart(userId: String, listDetailProductModel: MutableList<DetailProductModel>, cartAdapter: CartAdapter, updateUI: () -> Unit) {
        var listId = mutableListOf<String>()

        firestore.collection("USERS")
            .document(userId)
            .collection("USER_DATA")
            .document("MY_CART")
            .get()
            .addOnSuccessListener { document ->
                var id: String
                listId = document.get("list_product_id") as MutableList<String>
                for (i in 0..listId.size - 1) {
                    id = listId[i]
                    firestore.collection("PRODUCTS")
                        .document(id)
                        .get()
                        .addOnSuccessListener { document ->
                            listDetailProductModel.add(document.toObject(DetailProductModel::class.java)!!)
                            val z = 0
                            cartAdapter.notifyItemInserted(listDetailProductModel.size - 1)
                            if (i == listId.size - 1) {
                                updateUI.invoke()
                            }
                        }
                        .addOnFailureListener {
                            Log.d("firestore_error", "loadDetailProductInCart: " + it)
                        }
                }
            }
            .addOnFailureListener {
                Log.d("firestore_error", "loadListIdProduct: " + it)
            }
    }

    fun deleteItemInCart(id: String, inprogressUI: () -> Unit, updateUI: () -> Unit, updateAdapter: () -> Unit, context: Context) {
        if (!PreferenceHelper.firebaseUser?.uid.isNullOrEmpty()) {
            inprogressUI.invoke()
            firestore.collection("USERS")
                .document(PreferenceHelper.firebaseUser?.uid!!)
                .collection("USER_DATA")
                .document("MY_CART")
                .update("list_product_id", FieldValue.arrayRemove(id))
                .addOnSuccessListener {
                    updateUI.invoke()
                    updateAdapter.invoke()
                    Toast.makeText(context, R.string.delete_cart_item_successfully, Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, R.string.delete_cart_item_failed, Toast.LENGTH_SHORT).show()
                }
        }

    }

}
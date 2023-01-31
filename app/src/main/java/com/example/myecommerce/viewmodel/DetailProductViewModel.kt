package com.example.myecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myecommerce.model.DetailProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailProductViewModel : ViewModel() {
    private var firestore = Firebase.firestore
    var detailProductModelLiveData= MutableLiveData<DetailProductModel>()

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

}
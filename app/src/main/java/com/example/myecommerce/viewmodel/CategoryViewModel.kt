package com.example.myecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myecommerce.adapter.CategoryAdapter
import com.example.myecommerce.model.CategoryModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CategoryViewModel : ViewModel() {
    private var firestore = Firebase.firestore
    private lateinit var documentDetail: CategoryModel

    fun loadCategoryFromFirestore(categoryAdapter: CategoryAdapter, listCategoryModel: MutableList<CategoryModel>){
        firestore.collection("CATEGORIES").orderBy("index").get()
            .addOnSuccessListener { document ->
                for (documentSnapshot in document) {
                    documentDetail = documentSnapshot.toObject(CategoryModel::class.java)
                    listCategoryModel.add(CategoryModel(documentDetail.index, documentDetail.categoryIconLink, documentDetail.categoryContent))
                    categoryAdapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {
                Log.d("error_firestore", "get categoryfromfirestore -> categoryViewModel ")
            }
    }
}
package com.example.myecommerce.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myecommerce.adapter.HorizontalProductAdapter
import com.example.myecommerce.model.BannerModel
import com.example.myecommerce.model.HorizontalProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DealOfTheDayViewModel : ViewModel() {
    private var firestore = Firebase.firestore
    private lateinit var documentDetail: HorizontalProductModel

    fun loadHomePageDealOfTheDay (listProductDealOfTheDay: MutableList<HorizontalProductModel>, horizontalProductAdapter: HorizontalProductAdapter) {
        firestore.collection("CATEGORIES")
            .document("HOME")
            .collection("TOP_DEALS")
            .orderBy("index")
            .get()
            .addOnSuccessListener { document ->
                for (documentSnapshot in document) {
                    when (documentSnapshot.get("view_type").toString().toInt()) {
                        1 -> {
                            val countProduct = documentSnapshot.get("sum_of_products").toString().toInt()
                            for (i in 1..countProduct) {
                                listProductDealOfTheDay.add(
                                    HorizontalProductModel(
                                        documentSnapshot.get("product_id_" + i) as String,
                                        documentSnapshot.get("product_image_" + i) as String,
                                        documentSnapshot.get("product_title_" + i) as String,
                                        documentSnapshot.get("product_subtitle_" + i) as String,
                                        documentSnapshot.get("product_price_" + i) as String
                                    )
                                )
                            }
                            horizontalProductAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.d("error_firestore", "LoadHomePageDealOfTheDay: " + it)
            }
    }

    fun loadCategoryDealOfTheDay (
        listProductDealOfTheDay: MutableList<HorizontalProductModel>,
        horizontalProductAdapter: HorizontalProductAdapter,
        categoryStrID: String) {
        firestore.collection("CATEGORIES")
            .document(categoryStrID)
            .collection("TOP_DEALS")
            .orderBy("index")
            .get()
            .addOnSuccessListener { document ->
                for (documentSnapshot in document) {
                    when (documentSnapshot.get("view_type").toString().toInt()) {
                        1 -> {
                            val countProduct = documentSnapshot.get("sum_of_products").toString().toInt()
                            for (i in 1..countProduct) {
                                listProductDealOfTheDay.add(
                                    HorizontalProductModel(
                                        documentSnapshot.get("product_id_" + i) as String,
                                        documentSnapshot.get("product_image_" + i) as String,
                                        documentSnapshot.get("product_title_" + i) as String,
                                        documentSnapshot.get("product_subtitle_" + i) as String,
                                        documentSnapshot.get("product_price_" + i) as String
                                    )
                                )
                            }
                            horizontalProductAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.d("error_firestore", "LoadHomePageDealOfTheDay: " + it)
            }
    }
}
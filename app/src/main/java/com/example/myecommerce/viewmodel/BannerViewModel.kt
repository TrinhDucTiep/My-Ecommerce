package com.example.myecommerce.viewmodel

import android.icu.number.IntegerWidth
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myecommerce.adapter.BannerAdapter
import com.example.myecommerce.model.BannerModel
import com.google.api.LogDescriptor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class BannerViewModel : ViewModel() {
    private var firestore = Firebase.firestore

    fun loadHomePageBanners(bannerAdapter: BannerAdapter, listBannerModel: MutableList<BannerModel>, indicatorUpdate: () -> Unit) {
        firestore.collection("CATEGORIES")
            .document("HOME")
            .collection("TOP_DEALS")
            .orderBy("index")
            .get()
            .addOnSuccessListener {  document ->
                for (documentSnapshot in document) {
                    when (documentSnapshot.get("view_type").toString().toInt()) {
                        0 -> {
                            val countBanner: Int = documentSnapshot.get("sum_of_banners").toString().toInt()
                            for (i in 1..countBanner) {
                                listBannerModel.add(BannerModel(documentSnapshot.get("banner_" + i) as String))
                            }
                        }
                    }
                }
                bannerAdapter.notifyDataSetChanged()
                indicatorUpdate.invoke()
            }
            .addOnFailureListener {
                Log.d("error_firestore", "get bannerfromfirestore -> bannerViewModel " + it)
            }
    }
}
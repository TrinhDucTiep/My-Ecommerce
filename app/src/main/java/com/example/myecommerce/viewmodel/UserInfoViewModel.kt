package com.example.myecommerce.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myecommerce.R
import com.example.myecommerce.helper.PreferenceHelper
import com.example.myecommerce.model.AddressModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class UserInfoViewModel : ViewModel() {
    private var firestore = Firebase.firestore

    //address
    fun addAddress(titleAddress: String,
                   detailAddress: String,
                   updateUI: () -> Unit,
                   updateFail: () -> Unit) {
        if (!PreferenceHelper.firebaseUser?.uid.isNullOrEmpty()) {
            firestore.collection("USERS")
                .document(PreferenceHelper.firebaseUser?.uid!!)
                .collection("USER_DATA")
                .document("MY_ADDRESS")
                .update("address_model", FieldValue.arrayUnion(
                    hashMapOf(
                        "title_address" to titleAddress,
                        "detail_address" to detailAddress
                    )
                ))
                .addOnSuccessListener {
                    updateUI.invoke()
                }
                .addOnFailureListener {
                    updateFail.invoke()
                }
        }
    }

    fun loadAddress(updateUI: (String) -> Unit,
                    updateFail: () -> Unit) {
        if (!PreferenceHelper.firebaseUser?.uid.isNullOrEmpty()) {
            firestore.collection("USERS")
                .document(PreferenceHelper.firebaseUser?.uid!!)
                .collection("USER_DATA")
                .document("MY_ADDRESS")
                .get()
                .addOnSuccessListener {
                    updateUI.invoke(Gson().toJson(it.get("address_model")))
                }
                .addOnFailureListener {
                    updateFail.invoke()
                }
        }
    }

    fun updateAddress(index: Int,
                      titleAddress: String,
                      contentAddress: String,
                      updateUI: () -> Unit,
                      updateFail: () -> Unit) {
        if (!PreferenceHelper.firebaseUser?.uid.isNullOrEmpty()) {
            val docRef = firestore.collection("USERS")
                .document(PreferenceHelper.firebaseUser?.uid!!)
                .collection("USER_DATA")
                .document("MY_ADDRESS")

            docRef.get().addOnSuccessListener {
                //get json model
                val listJsonAddressModel = it.get("address_model") as MutableList<Map<String, Any>>
                //update json model
                listJsonAddressModel[index] = hashMapOf(
                    "title_address" to titleAddress,
                    "content_address" to contentAddress
                )
                //update to firestore
                docRef.update("address_model", listJsonAddressModel)
                    .addOnSuccessListener {
                        updateUI.invoke()
                    }
                    .addOnFailureListener {
                        updateFail.invoke()
                    }
            }
            .addOnFailureListener {
                updateFail.invoke()
            }
        }
    }

    fun deleteAddress(index: Int,
                      updateUI: () -> Unit,
                      updateFail: () -> Unit) {
        if (!PreferenceHelper.firebaseUser?.uid.isNullOrEmpty()) {
            val docRef = firestore.collection("USERS")
                .document(PreferenceHelper.firebaseUser?.uid!!)
                .collection("USER_DATA")
                .document("MY_ADDRESS")

            docRef.get().addOnSuccessListener {
                //get json model
                val listJsonAddressModel = it.get("address_model") as MutableList<Map<String, Any>>
                //update json model at index
                listJsonAddressModel.removeAt(index)
                //update to firestore
                docRef.update("address_model", listJsonAddressModel)
                    .addOnSuccessListener {
                        updateUI.invoke()
                    }
                    .addOnFailureListener {
                        updateFail.invoke()
                    }
            }
                .addOnFailureListener {
                    updateFail.invoke()
                }
        }
    }

}
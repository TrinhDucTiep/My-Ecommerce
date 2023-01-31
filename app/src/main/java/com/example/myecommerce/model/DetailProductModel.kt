package com.example.myecommerce.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class DetailProductModel(
    @DocumentId var id: String? = "",
    @get: PropertyName("1_star") @set: PropertyName("1_star") var star1: Long? = 0,
    @get: PropertyName("2_star") @set: PropertyName("2_star") var star2: Long? = 0,
    @get: PropertyName("3_star") @set: PropertyName("3_star") var star3: Long? = 0,
    @get: PropertyName("4_star") @set: PropertyName("4_star") var star4: Long? = 0,
    @get: PropertyName("5_star") @set: PropertyName("5_star") var star5: Long? = 0,
    @get: PropertyName("description") @set: PropertyName("description") var description: String? = "",
    @get: PropertyName("other_desc") @set: PropertyName("other_desc") var otherDesc: String? = "",
    @get: PropertyName("product_image") @set: PropertyName("product_image") var productImage: MutableList<String>? = mutableListOf(),
    @get: PropertyName("product_name") @set: PropertyName("product_name") var productName: String? = "",
    @get: PropertyName("product_old_price") @set: PropertyName("product_old_price") var productOldPrice: String? = "",
    @get: PropertyName("product_price") @set: PropertyName("product_price") var productPrice: String? = "",
    @get: PropertyName("spec_title") @set: PropertyName("spec_title") var specTitle: MutableList<String>? = mutableListOf(),
    @get: PropertyName("spec_value") @set: PropertyName("spec_value") var specValue: MutableList<String>? = mutableListOf(),
    @get: PropertyName("sum_of_sold") @set: PropertyName("sum_of_sold") var sumOfSold: Long? = 0
) {

}
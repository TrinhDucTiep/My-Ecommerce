package com.example.myecommerce.model

import com.google.firebase.firestore.PropertyName

class CategoryModel(
    @get: PropertyName("index") @set: PropertyName("index") var index: Int? = -1,
    @get: PropertyName("icon") @set: PropertyName("icon") var categoryIconLink: String? = "",
    @get: PropertyName("categoryName") @set: PropertyName("categoryName") var categoryContent: String? = "") {

}
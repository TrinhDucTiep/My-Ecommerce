package com.example.myecommerce.model

data class ItemCartProductModel(
    var shopName: String,
    var productImage: Int,
    var productName: String,
    var productType: String,
    var productOldPrice: String,
    var productPrice: String,
    var productNumber: Int ? = 1,
    var productVoucher: String ? = null
) {
}
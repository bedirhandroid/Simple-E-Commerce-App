package com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.product

data class ProductResponseDataItem(
    val id: Int? = null,
    val title: String? = null,
    val price: String? = null,
    val category: String? = null,
    val description: String? = null,
    val image: String? = null,
    val rating: RatingData? = null
)

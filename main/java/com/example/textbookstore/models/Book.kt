package com.example.textbookstore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val edition: String,
    val price: Int,
    val condition: String,
    val courseCode: String,
    val sellerName: String,
    val sellerEmail: String,
    val imageUrl: String? = null,
    val description: String
) : Parcelable
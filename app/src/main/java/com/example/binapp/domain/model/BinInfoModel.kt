package com.example.binapp.domain.model

import androidx.room.PrimaryKey

data class BinInfoModel(
    val bin: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val countryName: String,
    val countryLatitude: Double,
    val countryLongitude: Double,
    val bankName: String,
    val bankUrl: String?,
    val bankPhone: String?,
    val bankCity: String?
)
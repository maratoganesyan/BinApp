package com.example.binapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin")
data class BinEntity(
    @PrimaryKey val bin: String,
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


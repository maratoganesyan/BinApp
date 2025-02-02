package com.example.binapp.tools

import com.example.binapp.data.local.entity.BinEntity
import com.example.binapp.domain.model.Bank
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.BinInfoModel
import com.example.binapp.domain.model.BinNumber
import com.example.binapp.domain.model.Country

fun BinInfo.toEntity(bin: String): BinEntity {
    return BinEntity(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        countryName = country.name,
        countryLatitude = country.latitude,
        countryLongitude = country.longitude,
        bankName = bank.name,
        bankUrl = bank.url,
        bankPhone = bank.phone,
        bankCity = bank.city
    )
}

fun BinEntity.toBinInfo(): BinInfo {
    return BinInfo(
        number = BinNumber(
            length = bin.length,
            luhn = false
        ),
        scheme = scheme,
        type = type,
        brand = brand,
        country = Country(
            name = countryName,
            latitude = countryLatitude,
            longitude = countryLongitude
        ),
        bank = Bank(
            name = bankName,
            url = bankUrl,
            phone = bankPhone,
            city = bankCity
        )
    )
}

fun BinEntity.toBinInfoModel() : BinInfoModel{
    return BinInfoModel(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        countryName = countryName,
        countryLatitude = countryLatitude,
        countryLongitude = countryLongitude,
        bankName = bankName,
        bankUrl = bankUrl,
        bankPhone = bankPhone,
        bankCity = bankCity
    )
}
package com.example.binapp.data.api

import com.example.binapp.domain.model.BinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinListAPIService {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfo
}
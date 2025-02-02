package com.example.binapp.domain.repository

import com.example.binapp.data.local.entity.BinEntity
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.BinInfoModel
import kotlinx.coroutines.flow.Flow

interface BinRepositoryInterface {
    suspend fun getBinInfo(bin: String): BinInfo
    fun getBinHistory(): Flow<List<BinInfoModel>>
    suspend fun saveBinInfo(binInfo: BinInfo, bin : String)
    suspend fun deleteBinHistory()
}
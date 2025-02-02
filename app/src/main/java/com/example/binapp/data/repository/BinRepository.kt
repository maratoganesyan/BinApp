package com.example.binapp.data.repository

import com.example.binapp.data.api.BinListAPIService
import com.example.binapp.data.local.dao.BinDAO
import com.example.binapp.data.local.entity.BinEntity
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.BinInfoModel
import com.example.binapp.domain.repository.BinRepositoryInterface
import com.example.binapp.tools.toBinInfo
import com.example.binapp.tools.toBinInfoModel
import com.example.binapp.tools.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map

class BinRepository(
    private val api: BinListAPIService,
    private val dao: BinDAO
) : BinRepositoryInterface {

    override suspend fun getBinInfo(bin: String): BinInfo {
        return api.getBinInfo(bin).also { info ->
            dao.insert(info.toEntity(bin))
        }
    }

    override fun getBinHistory(): Flow<List<BinInfoModel>> {

        return dao.getAll().map { it -> it.map { it.toBinInfoModel() } }
    }

    override suspend fun saveBinInfo(binInfo: BinInfo, bin: String) {
        dao.insert(binInfo.toEntity(bin))
    }

    override suspend fun deleteBinHistory() {
        dao.deleteAll()
    }
}




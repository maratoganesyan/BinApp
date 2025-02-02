package com.example.binapp.domain.usecase

import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.BinInfoModel
import com.example.binapp.domain.repository.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow

class GetBinHistoryUseCase(
    private val repository: BinRepositoryInterface
) {
    operator fun invoke(): Flow<List<BinInfoModel>> {
        return repository.getBinHistory()
    }
}
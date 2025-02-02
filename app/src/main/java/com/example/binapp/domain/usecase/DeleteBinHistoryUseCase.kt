package com.example.binapp.domain.usecase

import com.example.binapp.domain.model.BinInfoModel
import com.example.binapp.domain.repository.BinRepositoryInterface
import kotlinx.coroutines.flow.Flow

class DeleteBinHistoryUseCase(
    private val repository: BinRepositoryInterface
) {
    suspend operator fun invoke(){
        repository.deleteBinHistory()
    }
}